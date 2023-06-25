package com.kendrareynolds.tanititourism.service;

import com.kendrareynolds.tanititourism.entity.ActionReport;
import com.kendrareynolds.tanititourism.entity.DoType;
import com.kendrareynolds.tanititourism.entity.Region;
import com.kendrareynolds.tanititourism.entity.ThingToDo;
import com.kendrareynolds.tanititourism.exception.DuplicateListingException;
import com.kendrareynolds.tanititourism.repository.ActionReportRepository;
import com.kendrareynolds.tanititourism.repository.ThingToDoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class AdminThingToDoService {

    public final ThingToDoRepository thingToDoRepository;
    public final ActionReportService actionReportService;
    public final ActionReportRepository actionReportRepository;


    public Page<ThingToDo> getAllThingsToDo(int page, int size) {
        return thingToDoRepository.findAll(PageRequest.of(page - 1, size));
    }

    public Optional<ThingToDo> getThingToDo(Long id) {
        return thingToDoRepository.findById(id);
    }

    @Transactional
    public void deleteThingToDo(Long id) {
        Optional<ThingToDo> thingToDoOptional = thingToDoRepository.findById(id);
        if (thingToDoOptional.isPresent()) {
            ThingToDo thingToDo = thingToDoOptional.get();
            List<ActionReport> actionReports = actionReportRepository.findByThingToDo(thingToDo);

            for (ActionReport actionReport : actionReports) {
                actionReport.setThingToDo(null);
                actionReportRepository.save(actionReport);
            }
            thingToDoRepository.delete(thingToDo);
        } else {
            throw new EntityNotFoundException("Thing To Do not found with id: " + id);
        }
    }

    public ThingToDo addThingToDo(ThingToDo newThingToDo, Region region, Set<DoType> doTypes, String username) {
        if (thingToDoRepository.findByName(newThingToDo.getName()).isPresent()) {
            throw new DuplicateListingException("A listing with this name already exists.");
        } else {
            newThingToDo.setRegion(region);
            for (DoType doType : doTypes) {
                newThingToDo.add(doType);
                doType.getThingsToDo().add(newThingToDo);
            }
            thingToDoRepository.save(newThingToDo);
            actionReportService.recordAction(username, "CREATE", newThingToDo);
            return newThingToDo;
        }
    }


    public ThingToDo updateThingToDo(Long id, ThingToDo updatedThingToDo, Region region, Set<DoType> doTypes, String username) {
        Optional<ThingToDo> thingToDoOptional = thingToDoRepository.findById(id);
        if (thingToDoOptional.isEmpty()) {
            throw new EntityNotFoundException("Thing To Do not found with id :: " + id);
        }
        ThingToDo existingThingToDo = thingToDoOptional.get();
        if (!existingThingToDo.getName().equals(updatedThingToDo.getName())) {
            Optional<ThingToDo> possibleDuplicate = thingToDoRepository.findByName(updatedThingToDo.getName());
            if (possibleDuplicate.isPresent()) {
                throw new DuplicateListingException("A listing with this name already exists.");
            }
        }
        setThingToDoAttributes(updatedThingToDo, existingThingToDo);
        existingThingToDo.setRegion(region);
        existingThingToDo.getDoTypes().clear();
        for (DoType doType : doTypes) {
            existingThingToDo.add(doType);
            doType.getThingsToDo().add(existingThingToDo);
        }
        thingToDoRepository.save(existingThingToDo);
        actionReportService.recordAction(username, "UPDATE", existingThingToDo);
        return existingThingToDo;
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
