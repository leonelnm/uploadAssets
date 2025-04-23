package com.codigo04.uploadassets.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "asset")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String originalFilename;

    private String mimeType;

    private long sizeInBytes;

    private String filePath;

    private String urlPublic;

    @Builder.Default
    private Boolean isDeleted = false;

    @CreationTimestamp
    private LocalDateTime createdAtDateTime;

    @PrePersist
    public void prePersist() {
        if(isDeleted == null) {
            isDeleted = false;
        }
    }

}
