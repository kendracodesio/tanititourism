package com.kendrareynolds.tanititourism.repository;

import com.kendrareynolds.tanititourism.entity.RestaurantsAndNightlife;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantsAndNightlifeRepository extends JpaRepository<RestaurantsAndNightlife, Long> {

    Page<RestaurantsAndNightlife> findByDeletedAtIsNull(Pageable pageable);
    Page<RestaurantsAndNightlife> findByDineTypeTypeNameAndDeletedAtIsNull(String typeName, Pageable pageable);
    Page<RestaurantsAndNightlife> findByRegionNameAndDeletedAtIsNull(String regionName, Pageable pageable);
    Page<RestaurantsAndNightlife> findByDineTypeTypeNameAndRegionNameAndDeletedAtIsNull(String typeName, String regionName, Pageable pageable);

}
