package com.kendrareynolds.tanititourism.controller;

import com.kendrareynolds.tanititourism.dto.CostRepresentation;
import com.kendrareynolds.tanititourism.entity.Cost;
import com.kendrareynolds.tanititourism.entity.DoType;
import com.kendrareynolds.tanititourism.entity.Region;
import com.kendrareynolds.tanititourism.entity.StayType;
import com.kendrareynolds.tanititourism.service.DoTypeService;
import com.kendrareynolds.tanititourism.service.RegionService;
import com.kendrareynolds.tanititourism.service.StayTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final DoTypeService doTypeService;
    private final RegionService regionService;
    private final StayTypeService stayTypeService;

    @Autowired
    public AdminController(DoTypeService doTypeService, RegionService regionService, StayTypeService stayTypeService) {
        this.stayTypeService = stayTypeService;
        this.doTypeService = doTypeService;
        this.regionService = regionService;
    }


    @GetMapping("/cost")
    public List<CostRepresentation> getCosts() {
        return Arrays.stream(Cost.values())
                .map(cost -> new CostRepresentation(cost.getName(), cost.getLabel()))
                .collect(Collectors.toList());

    }

    @GetMapping("/region")
    public List<Region> getRegions() {
        return regionService.findAll();
    }

    @GetMapping("/do-type")
    public List<DoType> getDoTypes() {
        return doTypeService.findAll();
    }

    @GetMapping("/stay-type")
    public List<StayType> getStayType() {
        return stayTypeService.findAll();
    }
}

