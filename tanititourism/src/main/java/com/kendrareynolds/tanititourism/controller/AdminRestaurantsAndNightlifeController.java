package com.kendrareynolds.tanititourism.controller;


import com.kendrareynolds.tanititourism.dto.RestaurantsAndNightlifeDto;
import com.kendrareynolds.tanititourism.entity.DineType;
import com.kendrareynolds.tanititourism.entity.Region;
import com.kendrareynolds.tanititourism.entity.RestaurantsAndNightlife;
import com.kendrareynolds.tanititourism.service.AdminRestaurantsAndNightlifeService;
import com.kendrareynolds.tanititourism.service.DineTypeService;
import com.kendrareynolds.tanititourism.service.RegionService;
import com.kendrareynolds.tanititourism.service.ResponseHelperService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/admin/api/restaurants-and-nightlife")
public class AdminRestaurantsAndNightlifeController {


    private final AdminRestaurantsAndNightlifeService adminRestaurantsAndNightlifeService;
    private final RegionService regionService;
    private final DineTypeService dineTypeService;
    private final ResponseHelperService responseHelperService;

    @Autowired
    AdminRestaurantsAndNightlifeController(AdminRestaurantsAndNightlifeService adminRestaurantsAndNightlifeService, RegionService regionService,
                                            DineTypeService dineTypeService, ResponseHelperService responseHelperService) {
        this.adminRestaurantsAndNightlifeService = adminRestaurantsAndNightlifeService;
        this.regionService = regionService;
        this.dineTypeService = dineTypeService;
        this.responseHelperService = responseHelperService;
    }

    @GetMapping("/list")
    public Page<RestaurantsAndNightlife> getAllRestaurantsAndNightlife(@RequestParam(required = false, defaultValue = "1") int page,
                                                                       @RequestParam(required = false, defaultValue = "6") int size) {
        return adminRestaurantsAndNightlifeService.getAllRestaurantsAndNightlife(page, size);
    }

    @GetMapping("/listing-detail/{id}")
    public Optional<RestaurantsAndNightlife> getRestaurantAndNightlife(@PathVariable("id") Long id) {
        return adminRestaurantsAndNightlifeService.getRestaurantAndNightlife(id);
    }

    @DeleteMapping("/delete-listing/{id}")
    public ResponseEntity<Void> deleteRestaurantsAndNightlife(@PathVariable Long id) {
        adminRestaurantsAndNightlifeService.deleteRestaurantAndNightlife(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/new-listing")
    public ResponseEntity<?> addRestaurantAndNightlife(@Valid @RequestBody RestaurantsAndNightlifeDto restaurantsAndNightlifeDto,
                                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseHelperService.getBindingErrors(bindingResult);
        }
        try {
            RestaurantsAndNightlife restaurantsAndNightlife = convertDtoToRestaurantAndNightlife(restaurantsAndNightlifeDto);
            Region region = regionService.getRegionById(restaurantsAndNightlifeDto.getRegionId());
            DineType dineType = dineTypeService.getDineTypeById(restaurantsAndNightlifeDto.getDineTypeId());
            RestaurantsAndNightlife savedRestaurantAndNightlife = adminRestaurantsAndNightlifeService.addRestaurantAndNightlife(restaurantsAndNightlife, region, dineType);
            return ResponseEntity.ok(savedRestaurantAndNightlife);
        } catch (Exception e) {
            System.err.println(e);
            return responseHelperService.getErrorResponse("Failed to create listing");
        }
    }

    @PutMapping("/update-listing/{id}")
    public ResponseEntity<?> updateRestaurantAndNightlife(@PathVariable Long id, @Valid @RequestBody RestaurantsAndNightlifeDto restaurantsAndNightlifeDto,
                                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseHelperService.getBindingErrors(bindingResult);
        }
        try {
            RestaurantsAndNightlife restaurantsAndNightlife = convertDtoToRestaurantAndNightlife(restaurantsAndNightlifeDto);
            Region region = regionService.getRegionById(restaurantsAndNightlifeDto.getRegionId());
            DineType dineType = dineTypeService.getDineTypeById(restaurantsAndNightlifeDto.getDineTypeId());
            RestaurantsAndNightlife savedRestaurantAndNightlife = adminRestaurantsAndNightlifeService.updateRestaurantAndNightlife(id, restaurantsAndNightlife, region, dineType);
            return ResponseEntity.ok(savedRestaurantAndNightlife);
        } catch (Exception e) {
            System.err.println(e);
            return responseHelperService.getErrorResponse("Failed to update listing");
        }
    }

    private RestaurantsAndNightlife convertDtoToRestaurantAndNightlife(RestaurantsAndNightlifeDto restaurantAndNightlifeDto) {
        RestaurantsAndNightlife restaurantsAndNightlife = new RestaurantsAndNightlife();
        restaurantsAndNightlife.setName(restaurantAndNightlifeDto.getName());
        restaurantsAndNightlife.setDescription(restaurantAndNightlifeDto.getDescription());
        restaurantsAndNightlife.setCost(restaurantAndNightlifeDto.getCost());
        restaurantsAndNightlife.setImageUrl(restaurantAndNightlifeDto.getImageUrl());
        restaurantsAndNightlife.setImageAltText(restaurantAndNightlifeDto.getImageAltText());
        restaurantsAndNightlife.setPhone(restaurantAndNightlifeDto.getPhone());
        restaurantsAndNightlife.setAcceptsReservations(restaurantAndNightlifeDto.getAcceptsReservations());
        return restaurantsAndNightlife;
    }
}
