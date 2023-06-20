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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
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


    public Page<ThingToDo> getAllThingsToDo(int page, int size) {
        return thingToDoRepository.findAll(PageRequest.of(page - 1, size));
    }

    public Optional<ThingToDo> getThingToDo(Long id) {
        return thingToDoRepository.findById(id);
    }

    public void deleteThingToDo(Long id) {
        thingToDoRepository.deleteById(id);
    }

    public ThingToDo addThingToDo(ThingToDo newThingToDo, Region region, Set<DoType> doTypes) {
        newThingToDo.setRegion(region);
        for (DoType doType : doTypes) {
            newThingToDo.add(doType);
            doType.getThingsToDo().add(newThingToDo);
        }
        thingToDoRepository.save(newThingToDo);
        return newThingToDo;
    }

    public ThingToDo updateThingToDo(Long id, ThingToDo updatedThingToDo, Region region, Set<DoType> doTypes) {
        Optional<ThingToDo> thingToDo = thingToDoRepository.findById(id);
        if (thingToDo.isPresent()) {
            ThingToDo existingThingToDo = thingToDo.get();
            setThingToDoAttributes(updatedThingToDo, existingThingToDo);
            existingThingToDo.setRegion(region);
            existingThingToDo.getDoTypes().clear();
            for (DoType doType : doTypes) {
                existingThingToDo.add(doType);
                doType.getThingsToDo().add(existingThingToDo);
            }
            thingToDoRepository.save(existingThingToDo);
            return existingThingToDo;
        } else {
            throw new EntityNotFoundException("Thing To Do not found with id: " + id);
        }
    }


    private void setThingToDoAttributes(ThingToDo frontEndThingToDo, ThingToDo databaseThingToDo) {
        databaseThingToDo.setName(frontEndThingToDo.getName());
        databaseThingToDo.setCost(frontEndThingToDo.getCost());
        databaseThingToDo.setPhone(frontEndThingToDo.getPhone());
        databaseThingToDo.setDescription(frontEndThingToDo.getDescription());
        databaseThingToDo.setImageUrl(frontEndThingToDo.getImageUrl());
        databaseThingToDo.setImageAltText(frontEndThingToDo.getImageAltText());
    }
}


//    public void updateThingToDo(ThingToDo updatedThingToDo, Long thingToDoId, Long regionId, Set<Long> doTypeIds) {
//        Optional<ThingToDo> thingToDo = thingToDoRepository.findById(thingToDoId);
//        if (thingToDo.isPresent()) {
//            ThingToDo existingThingToDo = thingToDo.get();
//            setThingToDoAttributes(updatedThingToDo, existingThingToDo);
//            setThingToDoRegion(regionId, existingThingToDo);
//            setThingToDoAndDoTypeRelationship(doTypeIds, existingThingToDo);
//            thingToDoRepository.save(existingThingToDo);
//        } else {
//            throw new EntityNotFoundException("Thing To Do not found with id: " + thingToDoId);
//        }
//    }


//    private void setThingToDoAndDoTypeRelationship(Set<DoType> doTypes, ThingToDo thingToDo) {
//        thingToDo.getDoTypes().clear();
//        for (DoType doType : doTypes) {
//            thingToDo.add(doType);
//            doType.getThingsToDo().add(thingToDo);
//        }
//    }
//
//    private void setThingToDoRegion(Region region, ThingToDo thingToDo) {
//            thingToDo.setRegion(region);
//        }
//    }



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