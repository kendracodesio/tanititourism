package com.kendrareynolds.tanititourism.service;

import com.kendrareynolds.tanititourism.entity.Region;
import com.kendrareynolds.tanititourism.entity.StayType;
import com.kendrareynolds.tanititourism.repository.StayTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public StayType getStayTypeById(Long id){
        Optional<StayType> optionalStayType = stayTypeRepository.findById(id);

        if(optionalStayType.isPresent()) {
            return optionalStayType.get();
        } else {
            throw new RuntimeException("Stay Type not found for id :: " + id);
        }
    }

    public StayType findByTypeName(String typeName) {
        Optional<StayType> optionalStayType = stayTypeRepository.findByTypeName(typeName);

        if(optionalStayType.isPresent()){
            return optionalStayType.get();
        } else {
            throw new RuntimeException("Stay Type not found for type name :: " + typeName);
        }
    }
}
