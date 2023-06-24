package com.kendrareynolds.tanititourism.controller;
import com.kendrareynolds.tanititourism.entity.DineType;
import com.kendrareynolds.tanititourism.entity.Region;
import com.kendrareynolds.tanititourism.entity.RestaurantsAndNightlife;
import com.kendrareynolds.tanititourism.service.DineTypeService;
import com.kendrareynolds.tanititourism.service.RegionService;
import com.kendrareynolds.tanititourism.service.RestaurantsAndNightlifeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/restaurants-and-nightlife")
public class RestaurantsAndNightlifeController {

    private final RestaurantsAndNightlifeService restaurantsAndNightlifeService;
    private final DineTypeService dineTypeService;
    private final RegionService regionService;

    @Autowired
    public RestaurantsAndNightlifeController(RestaurantsAndNightlifeService restaurantsAndNightlifeService, DineTypeService dineTypeService, RegionService regionService) {
        this.restaurantsAndNightlifeService = restaurantsAndNightlifeService;
        this.dineTypeService = dineTypeService;
        this.regionService = regionService;
    }

    @GetMapping("/type")
    public List<DineType> getDineTypes() {
        return dineTypeService.findAll();
    }

    @GetMapping("/region")
    public List<Region> getRegionTypes() {
        return regionService.findAll();
    }

    @GetMapping("/list")
    public Page<RestaurantsAndNightlife> getRestaurantsAndNightlife(@RequestParam(required = false, defaultValue = "all") String dineType,
                                                                    @RequestParam(required = false, defaultValue = "all") String region,
                                                                    @RequestParam(required = false, defaultValue = "1") int page,
                                                                    @RequestParam(required = false, defaultValue = "8") int size) {
        if(dineType.equals("all") && region.equals("all")) {
            return restaurantsAndNightlifeService.getAllRestaurantsAndNightlife( page, size);
        } else if (region.equals("all")) {
            return restaurantsAndNightlifeService.findByDineType(dineType, page, size);
        } else if (dineType.equals("all")) {
            return restaurantsAndNightlifeService.findByRegion(region, page, size);
        } else {
            return restaurantsAndNightlifeService.findByDineTypeAndRegion(dineType, region, page, size);
        }
    }
}
