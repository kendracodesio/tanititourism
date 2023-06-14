package com.kendrareynolds.tanititourism.repository;


import com.kendrareynolds.tanititourism.entity.ThingToDo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ThingToDoRepository extends JpaRepository<ThingToDo, Long> {

    Page<ThingToDo> findByDeletedAtIsNull(Pageable pageable);
    Page<ThingToDo> findByDoTypesTypeNameAndDeletedAtIsNull(String typeName, Pageable pageable);
    Page<ThingToDo> findByRegionNameAndDeletedAtIsNull(String regionName, Pageable pageable);
    Page<ThingToDo> findByDoTypesTypeNameAndRegionNameAndDeletedAtIsNull(String typeName, String regionName, Pageable pageable);


}
