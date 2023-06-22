package com.kendrareynolds.tanititourism.controller;

import com.kendrareynolds.tanititourism.dto.CostRepresentation;
import com.kendrareynolds.tanititourism.entity.*;
import com.kendrareynolds.tanititourism.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/api")
@RequiredArgsConstructor
public class AdminController {

    private final DoTypeService doTypeService;
    private final RegionService regionService;
    private final StayTypeService stayTypeService;
    private final DineTypeService dineTypeService;
    private final ActionReportService actionReportService;
    private final UserService userService;


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
    public List<StayType> getStayTypes() {
        return stayTypeService.findAll();
    }

    @GetMapping("/dine-type")
    public List<DineType> getDineTypes() {
        return dineTypeService.findAll();
    }

    @GetMapping("/user")
    public String getUsersFirstName(@RequestParam String username) {
        return userService.getUsersFirstName(username);
    }

    @GetMapping("/action-reports")
    public Page<ActionReport> getAllActionReports(@RequestParam(required = false, defaultValue = "1") int page,
                                                  @RequestParam(required = false, defaultValue = "20") int size) {
        return actionReportService.getAllActionReports(page, size);
    }

    @GetMapping("/user-recent-activity")
    public List<ActionReport> getUserRecentActivity(@RequestParam String username) {
        return actionReportService.getUserRecentActivity(username);
    }
}






