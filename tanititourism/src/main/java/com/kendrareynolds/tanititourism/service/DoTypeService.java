package com.kendrareynolds.tanititourism.service;

import com.kendrareynolds.tanititourism.entity.DoType;
import com.kendrareynolds.tanititourism.repository.DoTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DoTypeService {

    private final DoTypeRepository doTypeRepository;

    @Autowired
    public DoTypeService(DoTypeRepository doTypeRepository) {
        this.doTypeRepository = doTypeRepository;
    }
    public List<DoType> findAll() {
        return doTypeRepository.findAll();
    }
}
