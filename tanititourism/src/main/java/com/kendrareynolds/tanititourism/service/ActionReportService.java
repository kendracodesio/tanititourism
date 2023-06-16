package com.kendrareynolds.tanititourism.service;

import com.kendrareynolds.tanititourism.entity.*;
import com.kendrareynolds.tanititourism.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class ActionReportService {
    private final ActionReportRepository actionReportRepository;
    private final UserRepository userRepository;


    @Autowired
    public ActionReportService(ActionReportRepository actionReportRepository,
                               UserRepository userRepository) {
        this.actionReportRepository = actionReportRepository;
        this.userRepository = userRepository;
    }

    public void recordAction(String username, String action, Listing listing) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()) {
            throw new IllegalArgumentException("Username " + username + " not found");
        }
        User user = optionalUser.get();

        Listing.ListingType listingType = listing.getListingType();

        ActionReport report = new ActionReport();
        report.setUser(user);

        switch (listingType) {
            case DO -> {
                ThingToDo thingToDo = (ThingToDo) listing;
                report.setThingToDoId(thingToDo.getId());
            }
            case STAY -> {
                PlaceToStay placeToStay = (PlaceToStay) listing;
                report.setPlacesToStayId(placeToStay.getId());
            }
            case DINE -> {
                RestaurantsAndNightlife restaurantsAndNightlife = (RestaurantsAndNightlife) listing;
                report.setRestaurantsAndNightlifeId(restaurantsAndNightlife.getId());
            }
            default -> throw new IllegalArgumentException("Invalid listing type: " + listingType);
        }

        report.setAction(ActionReport.Action.valueOf(action.toUpperCase()));
        report.setTimestamp(OffsetDateTime.now());
        actionReportRepository.save(report);
    }
}