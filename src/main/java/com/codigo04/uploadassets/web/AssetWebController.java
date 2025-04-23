package com.codigo04.uploadassets.web;

import com.codigo04.uploadassets.service.AssetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/asset")
public class AssetWebController {

    private final AssetService assetService;

    public AssetWebController(AssetService assetService) {
        this.assetService = assetService;
    }


    @GetMapping
    public String formAssets() {
        return "assets";
    }

    @PostMapping
    public String createAsset(
            @RequestParam() MultipartFile file,
            @RequestParam(required = false) String name,
            Model model) {
        try {
            this.assetService.saveAsset(file, name);
            model.addAttribute("mensaje", "Fichero guardado!");
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al guardar fichero");
        }

        return "assets";
    }
}
