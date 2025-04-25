package com.codigo04.uploadassets.web;

import com.codigo04.uploadassets.service.AssetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/assets")
public class AssetWebController {

    private final AssetService assetService;

    public AssetWebController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping
    public String assets() {
        return "assetsPage";
    }

    @PostMapping
    public String createAsset(
            @RequestParam() MultipartFile file,
            @RequestParam(required = false) String name,
            RedirectAttributes redirectAttributes) {
        try {
            this.assetService.saveAsset(file, name);
            redirectAttributes.addFlashAttribute("success", "Fichero guardado!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar fichero. " + e.getMessage());
        }

        return "redirect:/assets";
    }
}
