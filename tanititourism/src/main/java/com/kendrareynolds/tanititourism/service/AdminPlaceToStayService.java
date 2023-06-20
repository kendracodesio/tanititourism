package com.kendrareynolds.tanititourism.service;

import com.kendrareynolds.tanititourism.entity.PlaceToStay;
import com.kendrareynolds.tanititourism.entity.Region;
import com.kendrareynolds.tanititourism.entity.StayType;
import com.kendrareynolds.tanititourism.repository.PlaceToStayRepository;
import com.kendrareynolds.tanititourism.repository.RegionRepository;
import com.kendrareynolds.tanititourism.repository.StayTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminPlaceToStayService {

    public final PlaceToStayRepository placeToStayRepository;
    public final StayTypeRepository stayTypeRepository;
    public final RegionRepository regionRepository;


    @Autowired
    public AdminPlaceToStayService(PlaceToStayRepository placeToStayRepository, StayTypeRepository stayTypeRepository, RegionRepository regionRepository) {
        this.placeToStayRepository = placeToStayRepository;
        this.stayTypeRepository = stayTypeRepository;
        this.regionRepository = regionRepository;
    }

    public Page<PlaceToStay> getAllPlacesToStay(int page, int size) {
        return placeToStayRepository.findAll(PageRequest.of(page - 1, size));
    }

    public Optional<PlaceToStay> getPlaceToStay(Long id) {
        return placeToStayRepository.findById(id);
    }

    public void deletePlaceToStay(Long id) {
        placeToStayRepository.deleteById(id);
    }

    public PlaceToStay addPlaceToStay(PlaceToStay newPlaceToStay, Region region, StayType stayType) {
        newPlaceToStay.setRegion(region);
        newPlaceToStay.setStayType(stayType);
        placeToStayRepository.save(newPlaceToStay);
        return newPlaceToStay;
    }

    public PlaceToStay updatePlaceToStay(Long id, PlaceToStay updatedPlaceToStay, Region region, StayType stayType) {
        Optional<PlaceToStay> placeToStay = placeToStayRepository.findById(id);
        if (placeToStay.isPresent()) {
            PlaceToStay existingPlaceToStay = placeToStay.get();
            setPlaceToStayAttributes(updatedPlaceToStay, existingPlaceToStay);
            existingPlaceToStay.setRegion(region);
            existingPlaceToStay.setStayType(stayType);
            placeToStayRepository.save(existingPlaceToStay);
            return existingPlaceToStay;
        } else {
            throw new EntityNotFoundException("Place To Stay not found with id " + id);
        }
    }

    private void setPlaceToStayAttributes(PlaceToStay frontEndPlaceToStay, PlaceToStay databasePlaceToStay) {
        databasePlaceToStay.setName(frontEndPlaceToStay.getName());
        databasePlaceToStay.setCost(frontEndPlaceToStay.getCost());
        databasePlaceToStay.setPhone(frontEndPlaceToStay.getPhone());
        databasePlaceToStay.setDescription(frontEndPlaceToStay.getDescription());
        databasePlaceToStay.setImageUrl(frontEndPlaceToStay.getImageUrl());
        databasePlaceToStay.setImageAltText(frontEndPlaceToStay.getImageAltText());
    }
}