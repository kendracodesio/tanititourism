package com.kendrareynolds.tanititourism.service;

import com.kendrareynolds.tanititourism.entity.*;
import com.kendrareynolds.tanititourism.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActionReportService {
    private final ActionReportRepository actionReportRepository;
    private final UserService userService;


    @Transactional
    public void recordAction(String username, String action, Listing listing) {
        Listing.ListingType listingType = listing.getListingType();
        User user = userService.findUserByUsername(username);
        ActionReport report = new ActionReport();
        report.setUser(user);

        switch (listingType) {

            case DO -> {

                ThingToDo thingToDo = (ThingToDo) listing;
                report.setThingToDo(thingToDo);
            }
            case STAY -> {

                PlaceToStay placeToStay = (PlaceToStay) listing;
                report.setPlaceToStay(placeToStay);
            }
            case DINE -> {

                RestaurantsAndNightlife restaurantsAndNightlife = (RestaurantsAndNightlife) listing;
                report.setRestaurantsAndNightlife(restaurantsAndNightlife);
            }
            default -> throw new IllegalArgumentException("Invalid listing type: " + listingType);
        }


        report.setAction(ActionReport.Action.valueOf(action.toUpperCase()));
        report.setTimestamp(LocalDateTime.now());
        actionReportRepository.save(report);
    }

    public Page<ActionReport> getAllActionReportsWithListings(int page, int size) {
        return actionReportRepository.findAllWithListingOrderedByTimestampDesc(PageRequest.of(page - 1, size));
    }

    public List<ActionReport> getUserRecentActivity(String username) {
        Page<ActionReport> page = actionReportRepository.findTop5ByUserMostRecent(username, PageRequest.of(0, 5));
        return page.getContent();
    }

    public List<ActionReport> getSearchResults(String searchQuery) {
        return actionReportRepository.searchByListingName(searchQuery.toLowerCase());
    }
}