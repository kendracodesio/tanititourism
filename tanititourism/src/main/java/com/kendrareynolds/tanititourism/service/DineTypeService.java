package com.kendrareynolds.tanititourism.service;

import com.kendrareynolds.tanititourism.entity.DineType;
import com.kendrareynolds.tanititourism.repository.DineTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DineTypeService {

    private final DineTypeRepository dineTypeRepository;

    @Autowired
    public DineTypeService(DineTypeRepository dineTypeRepository) {
        this.dineTypeRepository = dineTypeRepository;
    }

    public List<DineType> findAll() {
        return dineTypeRepository.findAll();
    }
}
