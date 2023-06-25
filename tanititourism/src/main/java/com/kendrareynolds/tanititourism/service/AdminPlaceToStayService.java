package com.kendrareynolds.tanititourism.service;

import com.kendrareynolds.tanititourism.entity.*;
import com.kendrareynolds.tanititourism.exception.DuplicateListingException;
import com.kendrareynolds.tanititourism.repository.ActionReportRepository;
import com.kendrareynolds.tanititourism.repository.PlaceToStayRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminPlaceToStayService {

    private final PlaceToStayRepository placeToStayRepository;
    private final ActionReportService actionReportService;
    private final ActionReportRepository actionReportRepository;


    public Page<PlaceToStay> getAllPlacesToStay(int page, int size) {
        return placeToStayRepository.findAll(PageRequest.of(page - 1, size));
    }

    public Optional<PlaceToStay> getPlaceToStay(Long id) {
        return placeToStayRepository.findById(id);
    }


    @Transactional
    public void deletePlaceToStay(Long id) {
        Optional<PlaceToStay> placeToStayOptional = placeToStayRepository.findById(id);
        if (placeToStayOptional.isPresent()) {
            PlaceToStay placeToStay = placeToStayOptional.get();
            List<ActionReport> actionReports = actionReportRepository.findByPlaceToStay(placeToStay);

            for (ActionReport actionReport : actionReports) {
                actionReport.setPlaceToStay(null);
                actionReportRepository.save(actionReport);
            }
            placeToStayRepository.delete(placeToStay);
        } else {
            throw new EntityNotFoundException("Place To Stay not found with id: " + id);
        }

    }

    public PlaceToStay addPlaceToStay(PlaceToStay newPlaceToStay, Region region, StayType stayType, String username) {
        if (placeToStayRepository.findByName(newPlaceToStay.getName()).isPresent()) {
            throw new DuplicateListingException("A listing with this name already exists.");
        } else {
            newPlaceToStay.setRegion(region);
            newPlaceToStay.setStayType(stayType);
            placeToStayRepository.save(newPlaceToStay);
            actionReportService.recordAction(username, "CREATE", newPlaceToStay);
            return newPlaceToStay;
        }
    }

    public PlaceToStay updatePlaceToStay(Long id, PlaceToStay updatedPlaceToStay, Region region, StayType stayType, String username) {
        Optional<PlaceToStay> placeToStayOptional = placeToStayRepository.findById(id);
        if (placeToStayOptional.isEmpty()) {
            throw new EntityNotFoundException("Place to Stay not found with id :: " + id);
        }
        PlaceToStay existingPlaceToStay = placeToStayOptional.get();
        if (!existingPlaceToStay.getName().equals(updatedPlaceToStay.getName())) {
            Optional<PlaceToStay> possibleDuplicate = placeToStayRepository.findByName(updatedPlaceToStay.getName());
            if (possibleDuplicate.isPresent()) {
                throw new DuplicateListingException("A listing with this name already exists");
            }
        }
        setPlaceToStayAttributes(updatedPlaceToStay, existingPlaceToStay);
        existingPlaceToStay.setRegion(region);
        existingPlaceToStay.setStayType(stayType);
        placeToStayRepository.save(existingPlaceToStay);
        actionReportService.recordAction(username, "UPDATE", existingPlaceToStay);
        return existingPlaceToStay;
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