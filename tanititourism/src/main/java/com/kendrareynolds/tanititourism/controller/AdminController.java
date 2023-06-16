package com.kendrareynolds.tanititourism.controller;

import com.kendrareynolds.tanititourism.dto.CostRepresentation;
import com.kendrareynolds.tanititourism.entity.Cost;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @GetMapping("costs")
    public List<CostRepresentation> getCosts() {
        return Arrays.stream(Cost.values())
                .map(cost -> new CostRepresentation(cost.getName(), cost.getLabel()))
                .collect(Collectors.toList());
    }
}
