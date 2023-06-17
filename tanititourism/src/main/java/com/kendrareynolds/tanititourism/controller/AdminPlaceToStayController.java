package com.kendrareynolds.tanititourism.controller;

import com.kendrareynolds.tanititourism.entity.PlaceToStay;
import com.kendrareynolds.tanititourism.service.AdminPlaceToStayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/places-to-stay")
public class AdminPlaceToStayController {

    private final AdminPlaceToStayService adminPlaceToStayService;

    @Autowired
    AdminPlaceToStayController(AdminPlaceToStayService adminPlaceToStayService) {
        this.adminPlaceToStayService = adminPlaceToStayService;
    }

    @GetMapping("/list")
    public List<PlaceToStay> getAllPlacesToStay() {
        return adminPlaceToStayService.getAllPlacesToStay();
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
}
