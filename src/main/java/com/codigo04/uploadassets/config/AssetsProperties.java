package com.codigo04.uploadassets.config;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "app.assets")
@Getter
@Setter
@Validated
public class AssetsProperties {

    @NotBlank
    private String path;

    @NotBlank
    private String urlPublic;

    @Min(1)
    private long maxSize;

}
