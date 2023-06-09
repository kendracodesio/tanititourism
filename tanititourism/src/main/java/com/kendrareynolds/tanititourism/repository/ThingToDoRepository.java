package com.kendrareynolds.tanititourism.repository;


import com.kendrareynolds.tanititourism.entity.ThingToDo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ThingToDoRepository extends JpaRepository<ThingToDo, Long> {
    Page<ThingToDo> findAll(Pageable pageable);
    Page<ThingToDo> findByDoTypesTypeName(String typeName, Pageable pageable);
    Page<ThingToDo> findByRegionName(String regionName, Pageable pageable);
    Page<ThingToDo> findByDoTypesTypeNameAndRegionName(String typeName, String regionName, Pageable pageable);


}
