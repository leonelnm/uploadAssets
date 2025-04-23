package com.codigo04.uploadassets.service;

import com.codigo04.uploadassets.models.Asset;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AssetService {

    List<Asset> findAll();
    void saveAsset(MultipartFile file, String name);
    void deleteAsset(Long id);

}
