package com.codigo04.uploadassets.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void saveAsset(String filePath, MultipartFile file);
    void deleteAsset(String filePath);

}
