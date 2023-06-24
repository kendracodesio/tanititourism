package com.kendrareynolds.tanititourism.service;

import com.kendrareynolds.tanititourism.entity.DoType;
import com.kendrareynolds.tanititourism.repository.DoTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public DoType findByTypeName(String typeName) {
        Optional<DoType> optionalDoType = doTypeRepository.findByTypeName(typeName);
        if (optionalDoType.isPresent()) {
            return optionalDoType.get();
        } else {
            throw new RuntimeException("Do type not found for type name :: " + typeName);
        }
    }


    public DoType getDoTypeById(Long id) {
        Optional<DoType> optionalDoType = doTypeRepository.findById(id);

        if (optionalDoType.isPresent()) {
            return optionalDoType.get();
        } else {
            throw new RuntimeException("Do Type not found for id :: " + id);
        }
    }

    public Set<DoType> getDoTypesByIds(Set<Long> doTypeIds) {
        Set<DoType> doTypes = new HashSet<>();
        for (Long doTypeId : doTypeIds) {
            DoType doType = doTypeRepository.findById(doTypeId)
                    .orElseThrow(() -> new EntityNotFoundException("DoType not found with id :: " + doTypeId));
            doTypes.add(doType);
        }
        return doTypes;
    }


}



