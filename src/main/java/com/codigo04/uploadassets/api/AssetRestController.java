package com.codigo04.uploadassets.api;

import com.codigo04.uploadassets.models.Asset;
import com.codigo04.uploadassets.service.AssetService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping
    public void saveAsset(@RequestBody MultipartFile file, @RequestParam(required = false) String name) {
        assetService.saveAsset(file, name);
    }
}
