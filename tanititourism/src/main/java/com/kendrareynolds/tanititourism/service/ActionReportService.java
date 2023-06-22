package com.kendrareynolds.tanititourism.service;

import com.kendrareynolds.tanititourism.entity.*;
import com.kendrareynolds.tanititourism.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActionReportService {
    private final ActionReportRepository actionReportRepository;
    private final UserRepository userRepository;



    public void recordAction(String username, String action, Listing listing) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("Username " + username + " not found");
        }
        User user = optionalUser.get();

        Listing.ListingType listingType = listing.getListingType();

        ActionReport report = new ActionReport();
        report.setUser(user);

        switch (listingType) {
            case DO -> {
                ThingToDo thingToDo = (ThingToDo) listing;
                report.setThingToDo(thingToDo);
            }
            case STAY -> {
                PlaceToStay placeToStay = (PlaceToStay) listing;
                report.setPlacesToStay(placeToStay);
            }
            case DINE -> {
                RestaurantsAndNightlife restaurantsAndNightlife = (RestaurantsAndNightlife) listing;
                report.setRestaurantsAndNightlife(restaurantsAndNightlife);
            }
            default -> throw new IllegalArgumentException("Invalid listing type: " + listingType);
        }

        report.setAction(ActionReport.Action.valueOf(action.toUpperCase()));
        report.setTimestamp(OffsetDateTime.now());
        user.addActionReport(report);
        actionReportRepository.save(report);
    }

    public Page<ActionReport> getAllActionReports(int page, int size) {
        return actionReportRepository.findAll(PageRequest.of(page - 1, size));
    }

    public List<ActionReport> getUserRecentActivity(String username) {
        return actionReportRepository.findTop5ByUser_UsernameOrderByTimestampDesc(username);
    }
}