package com.kendrareynolds.tanititourism.repository;


import com.kendrareynolds.tanititourism.entity.Listing;
import com.kendrareynolds.tanititourism.entity.ThingToDo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ThingToDoRepository extends JpaRepository<ThingToDo, Long> {

    Page<ThingToDo> findAll(Pageable pageable);
    Page<ThingToDo> findByDoTypesTypeName(String typeName, Pageable pageable);
    Page<ThingToDo> findByRegionName(String regionName, Pageable pageable);
    Page<ThingToDo> findByDoTypesTypeNameAndRegionName(String typeName, String regionName, Pageable pageable);
    Optional<ThingToDo> findByName(String name);

}
