package com.codigo04.uploadassets.repository;

import com.codigo04.uploadassets.models.Asset;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssetRepository extends CrudRepository<Asset, Long> {

    Optional<Asset> findByNameAndIsDeletedFalse(String name);

}
