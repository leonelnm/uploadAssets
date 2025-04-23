package com.codigo04.uploadassets.api;

import com.codigo04.uploadassets.models.Asset;
import com.codigo04.uploadassets.service.AssetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetRestController {

    private final AssetService assetService;

    public AssetRestController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping
    public List<Asset> getAssets() {
        return assetService.findAll();
    }
}
