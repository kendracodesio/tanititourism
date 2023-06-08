package com.kendrareynolds.tanititourism.repository;


import com.kendrareynolds.tanititourism.entity.ThingToDo;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ThingToDoRepository extends JpaRepository<ThingToDo, Long> {
    List<ThingToDo> findByDoTypesTypeName(String typeName);
    List<ThingToDo> findByRegionName(String regionName);
    List<ThingToDo> findByDoTypesTypeNameAndRegionName(String typeName, String regionName);


}
