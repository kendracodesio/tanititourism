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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@RestController
@RequestMapping("/admin/things-to-do")
public class AdminThingToDoController {

    private final AdminThingToDoService adminThingToDoService;
    private final ResponseHelperService responseHelperService;
    private final RegionService regionService;
    private final DoTypeService doTypeService;

    @Autowired
    AdminThingToDoController(AdminThingToDoService adminThingToDoService, ResponseHelperService responseHelperService,
                             RegionService regionService, DoTypeService doTypeService) {
        this.adminThingToDoService = adminThingToDoService;
        this.responseHelperService = responseHelperService;
        this.regionService = regionService;
        this.doTypeService = doTypeService;
    }

    @GetMapping("/list")
    public Page<ThingToDo> getAllThingsToDo(@RequestParam(required = false, defaultValue = "1") int page,
                                            @RequestParam(required = false, defaultValue = "6") int size) {
        return adminThingToDoService.getAllThingsToDo(page, size);
    }

    @GetMapping("/listing-detail/{id}")
    public Optional<ThingToDo> getThingToDo(@PathVariable("id") Long id) {
        return adminThingToDoService.getThingToDo(id);
    }


    @DeleteMapping("/delete-listing/{id}")
    public ResponseEntity<Void> deleteThingToDo(@PathVariable Long id) {
        adminThingToDoService.deleteThingToDo(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/new-listing")
    public ResponseEntity<?> addThingToDo(@Valid @RequestBody ThingToDoDto thingToDoDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            ThingToDo thingToDo = convertDtoToThingToDo(thingToDoDto);
            Region region = regionService.getRegionById(thingToDoDto.getRegionId());
            Set<DoType> doTypes = doTypeService.getDoTypesByIds(thingToDoDto.getDoTypesIds());
            adminThingToDoService.addThingToDo(thingToDo, region, doTypes);
            return responseHelperService.getSuccessResponse("Listing created successfully!");
        } catch (Exception e) {
            System.err.println(e);
            return responseHelperService.getErrorResponse("Failed to create listing");
        }
    }

    @PutMapping("/update-listing/{id}")
    public ResponseEntity<?> updateThingToDo(@PathVariable Long id, @Valid @RequestBody ThingToDoDto thingToDoDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            ThingToDo thingToDo = convertDtoToThingToDo(thingToDoDto);
            Region region = regionService.getRegionById(thingToDoDto.getRegionId());
            Set<DoType> doTypes = doTypeService.getDoTypesByIds(thingToDoDto.getDoTypesIds());
            adminThingToDoService.updateThingToDo(id, thingToDo, region, doTypes);
            return responseHelperService.getSuccessResponse("Listing updated successfully!");
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