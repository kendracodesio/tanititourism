package com.kendrareynolds.tanititourism.service;

import com.kendrareynolds.tanititourism.entity.StayType;
import com.kendrareynolds.tanititourism.repository.StayTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StayTypeService {

    private final StayTypeRepository stayTypeRepository;

    @Autowired
    public StayTypeService(StayTypeRepository stayTypeRepository) {
        this.stayTypeRepository = stayTypeRepository;
    }

    public List<StayType> findAll() {
        return stayTypeRepository.findAll();
    }
}
