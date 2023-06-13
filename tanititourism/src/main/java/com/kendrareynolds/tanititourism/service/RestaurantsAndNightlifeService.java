package com.kendrareynolds.tanititourism.service;

import com.kendrareynolds.tanititourism.entity.RestaurantsAndNightlife;
import com.kendrareynolds.tanititourism.repository.RestaurantsAndNightlifeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RestaurantsAndNightlifeService {

    private final RestaurantsAndNightlifeRepository restaurantsAndNightlifeRepository;

    @Autowired
    public RestaurantsAndNightlifeService(RestaurantsAndNightlifeRepository restaurantsAndNightlifeRepository) {
        this.restaurantsAndNightlifeRepository = restaurantsAndNightlifeRepository;
    }

    public Page<RestaurantsAndNightlife> findAll(int page, int size) {
        return restaurantsAndNightlifeRepository.findAll(PageRequest.of(page - 1, size));
    }

    public Page<RestaurantsAndNightlife> findByDineType(String dineTypeName, int page, int size) {
        return restaurantsAndNightlifeRepository.findByDineTypeTypeName(dineTypeName, PageRequest.of(page - 1, size));
    }

    public Page<RestaurantsAndNightlife> findByRegion(String regionName, int page, int size) {
        return restaurantsAndNightlifeRepository.findByRegionName(regionName, PageRequest.of(page - 1, size));
    }

    public Page<RestaurantsAndNightlife> findByDineTypeAndRegion(String dineTypeName, String regionName, int page, int size) {
        return restaurantsAndNightlifeRepository.findByDineTypeTypeNameAndRegionName(dineTypeName, regionName, PageRequest.of(page - 1, size));
    }



}
