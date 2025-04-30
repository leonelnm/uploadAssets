package com.codigo04.uploadassets.api;

import com.codigo04.uploadassets.api.dto.AssetDto;
import com.codigo04.uploadassets.api.dto.BaseResponse;
import com.codigo04.uploadassets.models.Asset;
import com.codigo04.uploadassets.service.AssetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> saveAsset(@RequestBody MultipartFile file, @RequestParam(required = false) String name) {
        AssetDto dto = assetService.saveAsset(file, name);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dto);

    }

    @PostMapping("/multiple")
    public void saveMultipleAssets(@RequestParam("files") MultipartFile[] files) {
        for (MultipartFile file : files) {
            assetService.saveAsset(file, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteAsset(@PathVariable Long id) {
        assetService.deleteAsset(id);
        return ResponseEntity.ok(new BaseResponse("asset.deleted.successfully"));
    }
}
