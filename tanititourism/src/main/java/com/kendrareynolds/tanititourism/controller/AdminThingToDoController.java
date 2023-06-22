package com.kendrareynolds.tanititourism.controller;

import com.kendrareynolds.tanititourism.dto.ThingToDoDto;
import com.kendrareynolds.tanititourism.entity.DoType;
import com.kendrareynolds.tanititourism.entity.Region;
import com.kendrareynolds.tanititourism.entity.ThingToDo;
import com.kendrareynolds.tanititourism.service.AdminThingToDoService;
import com.kendrareynolds.tanititourism.service.DoTypeService;
import com.kendrareynolds.tanititourism.service.RegionService;
import com.kendrareynolds.tanititourism.service.ResponseHelperService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/api/things-to-do")
public class AdminThingToDoController {

    private final AdminThingToDoService adminThingToDoService;
    private final ResponseHelperService responseHelperService;
    private final RegionService regionService;
    private final DoTypeService doTypeService;


    @GetMapping("/list")
    public Page<ThingToDo> getAllThingsToDo(@RequestParam(required = false, defaultValue = "1") int page,
                                            @RequestParam(required = false, defaultValue = "10") int size) {
        return adminThingToDoService.getAllThingsToDo(page, size);
    }

    @GetMapping("/listing-detail/{id}")
    public Optional<ThingToDo> getThingToDo(@PathVariable Long id) {
        return adminThingToDoService.getThingToDo(id);
    }


    @DeleteMapping("/delete-listing/{id}")
    public ResponseEntity<Void> deleteThingToDo(@PathVariable Long id) {
        adminThingToDoService.deleteThingToDo(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/new-listing")
    public ResponseEntity<?> addThingToDo(@Valid @RequestBody ThingToDoDto thingToDoDto,
                                          @RequestHeader("X-Username") String username,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseHelperService.getBindingErrors(bindingResult);
        }
        try {
            ThingToDo thingToDo = convertDtoToThingToDo(thingToDoDto);
            Region region = regionService.getRegionById(thingToDoDto.getRegionId());
            Set<DoType> doTypes = doTypeService.getDoTypesByIds(thingToDoDto.getDoTypesIds());
            ThingToDo savedThingToDo = adminThingToDoService.addThingToDo(thingToDo, region, doTypes, username);
            return ResponseEntity.ok(savedThingToDo);
        } catch (Exception e) {
            System.err.println(e);
            return responseHelperService.getErrorResponse("Failed to create listing");
        }
    }

    @PutMapping("/update-listing/{id}")
    public ResponseEntity<?> updateThingToDo(@PathVariable Long id, @Valid @RequestBody ThingToDoDto thingToDoDto,
                                             @RequestHeader("X-Username") String username,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseHelperService.getBindingErrors(bindingResult);
        }
        try {
            ThingToDo thingToDo = convertDtoToThingToDo(thingToDoDto);
            Region region = regionService.getRegionById(thingToDoDto.getRegionId());
            Set<DoType> doTypes = doTypeService.getDoTypesByIds(thingToDoDto.getDoTypesIds());
            ThingToDo savedThingToDo = adminThingToDoService.updateThingToDo(id, thingToDo, region, doTypes, username);
            return ResponseEntity.ok(savedThingToDo);
        } catch (Exception e) {
            System.err.println(e);
            return responseHelperService.getErrorResponse("Failed to update listing");
        }
    }

    private ThingToDo convertDtoToThingToDo(ThingToDoDto thingToDoDto) {
        ThingToDo thingToDo = new ThingToDo();
        thingToDo.setName(thingToDoDto.getName());
        thingToDo.setDescription(thingToDoDto.getDescription());
        thingToDo.setCost(thingToDoDto.getCost());
        thingToDo.setImageUrl(thingToDoDto.getImageUrl());
        thingToDo.setImageAltText(thingToDoDto.getImageAltText());
        thingToDo.setPhone(thingToDoDto.getPhone());
        return thingToDo;
    }

}