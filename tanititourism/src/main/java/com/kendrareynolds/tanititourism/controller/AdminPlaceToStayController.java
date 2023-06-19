package com.kendrareynolds.tanititourism.controller;

import com.kendrareynolds.tanititourism.dto.PlaceToStayDto;
import com.kendrareynolds.tanititourism.entity.PlaceToStay;
import com.kendrareynolds.tanititourism.entity.Region;
import com.kendrareynolds.tanititourism.entity.StayType;
import com.kendrareynolds.tanititourism.service.AdminPlaceToStayService;
import com.kendrareynolds.tanititourism.service.RegionService;
import com.kendrareynolds.tanititourism.service.ResponseHelperService;
import com.kendrareynolds.tanititourism.service.StayTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/admin/places-to-stay")
public class AdminPlaceToStayController {

    private final AdminPlaceToStayService adminPlaceToStayService;
    private final RegionService regionService;
    private final StayTypeService stayTypeService;
    private final ResponseHelperService responseHelperService;

    @Autowired
    AdminPlaceToStayController(AdminPlaceToStayService adminPlaceToStayService, RegionService regionService,
                               StayTypeService stayTypeService, ResponseHelperService responseHelperService) {
        this.adminPlaceToStayService = adminPlaceToStayService;
        this.regionService = regionService;
        this.stayTypeService = stayTypeService;
        this.responseHelperService = responseHelperService;
    }

    @GetMapping("/list")
    public Page<PlaceToStay> getAllPlacesToDo(@RequestParam(required = false, defaultValue = "1") int page,
                                            @RequestParam(required = false, defaultValue = "6") int size){
        return adminPlaceToStayService.getAllPlacesToStay(page, size);
    }

    @GetMapping("/listing-detail/{id}")
    public Optional<PlaceToStay> getPlaceToStay(@PathVariable("id") Long id){
        return adminPlaceToStayService.getPlaceToStay(id);
    }

    @DeleteMapping("/delete-listing/{id}")
    public ResponseEntity<Void> deleteThingToDo(@PathVariable Long id) {
        adminPlaceToStayService.deleteThingToDo(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/new-listing")
    public ResponseEntity<?> addPlaceToStay(@Valid @RequestBody PlaceToStayDto placeToStayDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
           return responseHelperService.getBindingErrors(bindingResult);
        }
        try {
            PlaceToStay placeToStay = convertDtoToPlaceToStay(placeToStayDto);
            Region region = regionService.getRegionById(placeToStayDto.getRegionId());
            StayType stayType = stayTypeService.getStayTypeById(placeToStayDto.getStayTypeId());
            adminPlaceToStayService.addPlaceToStay(placeToStay, region, stayType);
            return responseHelperService.getSuccessResponse("Listing created successfully");
        } catch (Exception e){
            System.err.println(e);
            return responseHelperService.getErrorResponse("Failed to create listing");
        }
    }

    @PutMapping("/update-listing/{id}")
    public ResponseEntity<?> updatePlaceToStay(@PathVariable Long id, @Valid @RequestBody PlaceToStayDto placeToStayDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseHelperService.getBindingErrors(bindingResult);
        }
        try {
            PlaceToStay placeToStay = convertDtoToPlaceToStay(placeToStayDto);
            Region region = regionService.getRegionById(placeToStayDto.getRegionId());
            StayType stayType = stayTypeService.getStayTypeById(placeToStayDto.getStayTypeId());
            adminPlaceToStayService.updatePlaceToStay(id, placeToStay, region, stayType);
            return responseHelperService.getSuccessResponse("Listing updated successfully");
        } catch (Exception e) {
            System.err.println(e);
            return responseHelperService.getErrorResponse("Failed to update listing");
        }
    }


    private PlaceToStay convertDtoToPlaceToStay(PlaceToStayDto placeToStayDto) {
        PlaceToStay placeToStay = new PlaceToStay();
        placeToStay.setName(placeToStayDto.getName());
        placeToStay.setDescription(placeToStayDto.getDescription());
        placeToStay.setCost(placeToStayDto.getCost());
        placeToStay.setImageUrl(placeToStayDto.getImageUrl());
        placeToStay.setImageAltText(placeToStayDto.getImageAltText());
        placeToStay.setPhone(placeToStayDto.getPhone());
        return placeToStay;
    }
}
