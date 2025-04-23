package com.codigo04.uploadassets.service.impl;

import com.codigo04.uploadassets.config.AssetsProperties;
import com.codigo04.uploadassets.models.Asset;
import com.codigo04.uploadassets.repository.AssetRepository;
import com.codigo04.uploadassets.service.AssetService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final AssetsProperties assetsProperties;

    public AssetServiceImpl(AssetRepository assetRepository, AssetsProperties assetsProperties) {
        this.assetRepository = assetRepository;
        this.assetsProperties = assetsProperties;
    }

    @Override
    public List<Asset> findAll() {
        return (List<Asset>) assetRepository.findAll();
    }


    @Override
    @Transactional
    public void saveAsset(MultipartFile file, String name) {
        if (file.getSize() > assetsProperties.getMaxSize()) {
            throw new RuntimeException("error.asset.max-size");
        }

        FileNameExtension fileNameExtension = getFileNameExtension(file.getOriginalFilename());
        String finalAssetName = (name != null && !name.isBlank()) ? name : fileNameExtension.name();
        String uniqueFileName = generateUniqueName(finalAssetName);
        String uniqueFileNameExtension = uniqueFileName + fileNameExtension.extension();
        String urlPublic = getUrlPublic(uniqueFileNameExtension);
        String filePath = getFilePath(uniqueFileNameExtension);

        Asset asset = Asset.builder()
                .name(finalAssetName)
                .originalFilename(file.getOriginalFilename())
                .mimeType(file.getContentType())
                .sizeInBytes(file.getSize())
                .urlPublic(urlPublic)
                .filePath(filePath)
                .build();

        this.saveAssetOnDisk(filePath, file);
        assetRepository.save(asset);

    }

    @Override
    public void deleteAsset(Long id) {
        Asset asset = assetRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("error.asset.no.existe"));
        assetRepository.delete(asset);
    }

    private String getFilePath(String uniqueFileNameExtension) {
        return assetsProperties.getPath() + "/" + uniqueFileNameExtension;
    }

    private void saveAssetOnDisk(String filePath, MultipartFile file) {
        try {
            Path path = Path.of(filePath);
            if (Files.exists(path)) {
                Files.delete(path);
            }
            Files.createDirectories(path.getParent());
            file.transferTo(path);
        } catch (IOException e) {
            throw new RuntimeException("error.asset.save-on-disk", e);
        }
    }

    private String getUrlPublic(String uniqueFileName) {
        return assetsProperties.getUrlPublic() + "/" + uniqueFileName;
    }

    private String generateUniqueName(String name) {
        String slug = name.replaceAll("[^a-zA-Z0-9]", "_");
        return slug + "_" + generateShortId();
    }

    private String generateShortId() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return sb.toString();
    }

    private FileNameExtension getFileNameExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return new FileNameExtension(filename != null ? filename : "", "");
        }

        int dotIndex = filename.lastIndexOf('.');
        String name = filename.substring(0, dotIndex);
        String ext = filename.substring(dotIndex); // incluye el punto
        return new FileNameExtension(name, ext);
    }

    private record FileNameExtension(String name, String extension) {
    }

}
