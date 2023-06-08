package com.kendrareynolds.tanititourism.service;

import com.kendrareynolds.tanititourism.entity.ThingToDo;
import com.kendrareynolds.tanititourism.repository.ThingToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThingToDoService {
    private final ThingToDoRepository thingToDoRepository;

    @Autowired
    public ThingToDoService(ThingToDoRepository thingToDoRepository) {
        this.thingToDoRepository = thingToDoRepository;
    }

    public List<ThingToDo> findAll() {
        return thingToDoRepository.findAll();
    }

    public List<ThingToDo> findByDoType(String doTypeName) {
        return thingToDoRepository.findByDoTypesTypeName(doTypeName);
    }

    public List<ThingToDo> findByRegion(String regionName) {
        return thingToDoRepository.findByRegionName(regionName);
    }

    public List<ThingToDo> findByDoTypeAndRegion(String doTypeName, String regionName) {
        return thingToDoRepository.findByDoTypesTypeNameAndRegionName(doTypeName, regionName);
    }
}
