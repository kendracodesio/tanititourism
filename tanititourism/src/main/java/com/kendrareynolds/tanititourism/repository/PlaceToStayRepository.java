package com.kendrareynolds.tanititourism.repository;

import com.kendrareynolds.tanititourism.entity.PlaceToStay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceToStayRepository extends JpaRepository<PlaceToStay, Long> {
    Page<PlaceToStay> findByDeletedAtIsNull(Pageable pageable);
    Page<PlaceToStay> findByStayTypeTypeNameAndDeletedAtIsNull(String typeName, Pageable pageable);
    Page<PlaceToStay> findByRegionNameAndDeletedAtIsNull(String regionName, Pageable pageable);
    Page<PlaceToStay> findByStayTypeTypeNameAndRegionNameAndDeletedAtIsNull(String typeName, String regionName, Pageable pageable);

}
