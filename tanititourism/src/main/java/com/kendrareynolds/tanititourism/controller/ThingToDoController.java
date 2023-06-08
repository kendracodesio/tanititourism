package com.kendrareynolds.tanititourism.controller;

import com.kendrareynolds.tanititourism.entity.DoType;
import com.kendrareynolds.tanititourism.entity.Region;
import com.kendrareynolds.tanititourism.entity.ThingToDo;
import com.kendrareynolds.tanititourism.service.DoTypeService;
import com.kendrareynolds.tanititourism.service.RegionService;
import com.kendrareynolds.tanititourism.service.ThingToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/things-to-do")
public class ThingToDoController {

    private final ThingToDoService thingToDoService;
    private final DoTypeService doTypeService;
    private final RegionService regionService;

    @Autowired
    public ThingToDoController(ThingToDoService thingToDoService, DoTypeService doTypeService, RegionService regionService) {
        this.thingToDoService = thingToDoService;
        this.doTypeService = doTypeService;
        this.regionService = regionService;
    };

    @GetMapping("/type")
    public List<DoType> getDoTypes(){
        return doTypeService.findAll();
    }

    @GetMapping("/region")
    public List<Region> getRegionTypes() {
        return regionService.findAll();
    }

    @GetMapping("/list")
    public List<ThingToDo> getThingsToDo(@RequestParam (required = false, defaultValue = "all") String doType,
                                         @RequestParam(required = false, defaultValue = "all") String region) {
        if (doType.equals("all") && region.equals("all")) {
            return thingToDoService.findAll();
        } else if (region.equals("all")) {
            return thingToDoService.findByDoType(doType);
        } else if (doType.equals("all")) {
            return thingToDoService.findByRegion(region);
        } else {
            return thingToDoService.findByDoTypeAndRegion(doType, region);
        }
    }
}
