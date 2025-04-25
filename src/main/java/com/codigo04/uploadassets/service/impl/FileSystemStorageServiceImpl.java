package com.codigo04.uploadassets.service.impl;

import com.codigo04.uploadassets.service.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileSystemStorageServiceImpl implements StorageService {


    @Override
    public void saveAsset(String filePath, MultipartFile file) {
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

    @Override
    public void deleteAsset(String filePath) {
        try {
            Path path = Path.of(filePath);
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException("error.asset.delete-on-disk", e);
        }
    }
}
