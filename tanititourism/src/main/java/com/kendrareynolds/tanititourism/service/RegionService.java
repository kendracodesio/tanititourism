package com.kendrareynolds.tanititourism.service;

import com.kendrareynolds.tanititourism.entity.Region;
import com.kendrareynolds.tanititourism.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {

    private final RegionRepository regionRepository;

    @Autowired
    public RegionService (RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    public Region getRegionById(Long id){
        Optional<Region> optionalRegion = regionRepository.findById(id);

        if(optionalRegion.isPresent()) {
            return optionalRegion.get();
        } else {
            throw new RuntimeException("Region not found for id :: " + id);
        }
    }

    public Region findByName(String name) {
        Optional<Region> optionalRegion = regionRepository.findByName(name);

        if(optionalRegion.isPresent()) {
            return optionalRegion.get();
        } else {
            throw new RuntimeException("Region not found for id :: " + name);
        }
    }

}
