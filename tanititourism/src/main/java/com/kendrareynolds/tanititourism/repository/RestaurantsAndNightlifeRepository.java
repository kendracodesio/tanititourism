package com.kendrareynolds.tanititourism.repository;

import com.kendrareynolds.tanititourism.entity.RestaurantsAndNightlife;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantsAndNightlifeRepository extends JpaRepository<RestaurantsAndNightlife, Long> {
    @Override
    Page<RestaurantsAndNightlife> findAll(Pageable pageable);
    Page<RestaurantsAndNightlife> findByDineTypeTypeName(String typeName, Pageable pageable);
    Page<RestaurantsAndNightlife> findByRegionName(String regionName, Pageable pageable);
    Page<RestaurantsAndNightlife> findByDineTypeTypeNameAndRegionName(String typeName, String regionName, Pageable pageable);

}
