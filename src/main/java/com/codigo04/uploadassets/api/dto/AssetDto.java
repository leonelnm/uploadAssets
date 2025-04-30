package com.codigo04.uploadassets.api.dto;

import com.codigo04.uploadassets.models.Asset;

public record AssetDto(String name, String mimeType, String urlPublic) {
    public AssetDto(Asset asset){
        this(asset.getName(), asset.getMimeType(), asset.getUrlPublic());
    }
}
