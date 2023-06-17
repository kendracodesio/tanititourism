package com.kendrareynolds.tanititourism.service;

import com.kendrareynolds.tanititourism.entity.DoType;
import com.kendrareynolds.tanititourism.entity.Region;
import com.kendrareynolds.tanititourism.entity.ThingToDo;
import com.kendrareynolds.tanititourism.repository.DoTypeRepository;
import com.kendrareynolds.tanititourism.repository.RegionRepository;
import com.kendrareynolds.tanititourism.repository.ThingToDoRepository;
import com.kendrareynolds.tanititourism.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class AdminThingToDoService {

    public final ThingToDoRepository thingToDoRepository;
    public final DoTypeRepository doTypeRepository;
    public final RegionRepository regionRepository;
    public final ActionReportService actionReportService;
    public final UserRepository userRepository;

    @Autowired
    public AdminThingToDoService(ThingToDoRepository thingToDoRepository, ActionReportService actionReportService,
                                 UserRepository userRepository, DoTypeRepository doTypeRepository, RegionRepository regionRepository) {
        this.thingToDoRepository = thingToDoRepository;
        this.actionReportService = actionReportService;
        this.userRepository = userRepository;
        this.doTypeRepository = doTypeRepository;
        this.regionRepository = regionRepository;
    }


    public List<ThingToDo> getAllThingsToDo() {
        return thingToDoRepository.findAll();
    }

    public Optional<ThingToDo> getThingToDo(Long id) {
        return thingToDoRepository.findById(id);
    }


    public void addThingToDo(ThingToDo thingToDo, Long regionId, Set<Long> doTypeIds) {
        ThingToDo newThingToDo = new ThingToDo();
        setThingToDoAttributes(thingToDo, newThingToDo);
        setThingToDoRegion(regionId, newThingToDo);
        setThingToDoAndDoTypeRelationship(doTypeIds, newThingToDo);
        thingToDoRepository.save(newThingToDo);

    }

    public void updateThingToDo(ThingToDo updatedThingToDo, Long thingToDoId, Long regionId, Set<Long> doTypeIds) {
        Optional<ThingToDo> thingToDo = thingToDoRepository.findById(thingToDoId);
        if (thingToDo.isPresent()) {
            ThingToDo existingThingToDo = thingToDo.get();
            setThingToDoAttributes(updatedThingToDo, existingThingToDo);
            setThingToDoRegion(regionId, existingThingToDo);
            setThingToDoAndDoTypeRelationship(doTypeIds, existingThingToDo);
            thingToDoRepository.save(existingThingToDo);
        } else {
            throw new EntityNotFoundException("Thing To Do not found with id: " + thingToDoId);
        }
    }

    public void deleteThingToDo(Long id) {
        thingToDoRepository.deleteById(id);
    }

    private void setThingToDoAttributes(ThingToDo frontEndThingToDo, ThingToDo databaseThingToDo) {
        databaseThingToDo.setName(frontEndThingToDo.getName());
        databaseThingToDo.setCost(frontEndThingToDo.getCost());
        databaseThingToDo.setPhone(frontEndThingToDo.getPhone());
        databaseThingToDo.setDescription(frontEndThingToDo.getDescription());
        databaseThingToDo.setImageUrl(frontEndThingToDo.getImageUrl());
        databaseThingToDo.setImageAltText(frontEndThingToDo.getImageAltText());
    }

    private void setThingToDoAndDoTypeRelationship(Set<Long> doTypeIds, ThingToDo thingToDo) {
        Set<DoType> doTypes = new HashSet<>();
        for (Long doTypeId : doTypeIds) {
            DoType doType = doTypeRepository.findById(doTypeId)
                    .orElseThrow(() -> new EntityNotFoundException("DoType not found with id: " + doTypeId));
            doTypes.add(doType);
        }

        thingToDo.getDoTypes().clear();
        for (DoType doType : doTypes) {
            thingToDo.add(doType);
            doType.getThingsToDo().add(thingToDo);
        }
    }

    private void setThingToDoRegion(Long regionId, ThingToDo thingToDo) {
        Optional<Region> optionalRegion = regionRepository.findById(regionId);
        if (optionalRegion.isPresent()) {
            thingToDo.setRegion(optionalRegion.get());
        } else {
            throw new EntityNotFoundException("Region not found with id: " + regionId);
        }
    }


}

//    public void addThingToDo(ThingToDo thingToDo, String username) {
//        ThingToDo savedThingToDo = thingToDoRepository.save(thingToDo);
//        actionReportService.recordAction(username, "CREATE", savedThingToDo);
//    }

//    public void updateThingToDo(ThingToDo thingToDo, String username) {
//        thingToDoRepository.findById(thingToDo.getId())
//                .orElseThrow(() -> new EntityNotFoundException("ThingToDo not found with id: " + thingToDo.getId()));
//        ThingToDo updatedThingToDo = thingToDoRepository.save(thingToDo);
//        actionReportService.recordAction(username, "UPDATE", updatedThingToDo);
//    }