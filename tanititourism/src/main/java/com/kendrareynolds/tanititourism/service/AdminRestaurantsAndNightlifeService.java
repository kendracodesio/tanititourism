package com.kendrareynolds.tanititourism.service;

import com.kendrareynolds.tanititourism.entity.DineType;
import com.kendrareynolds.tanititourism.entity.Region;
import com.kendrareynolds.tanititourism.entity.RestaurantsAndNightlife;
import com.kendrareynolds.tanititourism.repository.RestaurantsAndNightlifeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminRestaurantsAndNightlifeService {

    private final RestaurantsAndNightlifeRepository restaurantsAndNightlifeRepository;
    private final ActionReportService actionReportService;



    public Page<RestaurantsAndNightlife> getAllRestaurantsAndNightlife(int page, int size) {
        return restaurantsAndNightlifeRepository.findAll(PageRequest.of(page - 1, size));
    }

    public Optional<RestaurantsAndNightlife> getRestaurantAndNightlife(Long id) {
        return restaurantsAndNightlifeRepository.findById(id);
    }

    public void deleteRestaurantAndNightlife(Long id) {
        restaurantsAndNightlifeRepository.deleteById(id);
    }

    public RestaurantsAndNightlife addRestaurantAndNightlife(RestaurantsAndNightlife newRestaurantAndNightlife, Region region,
                                                             DineType dineType, String username) {
        newRestaurantAndNightlife.setRegion(region);
        newRestaurantAndNightlife.setDineType(dineType);
        restaurantsAndNightlifeRepository.save(newRestaurantAndNightlife);
        actionReportService.recordAction(username, "CREATE", newRestaurantAndNightlife);
        return newRestaurantAndNightlife;
    }

    public RestaurantsAndNightlife updateRestaurantAndNightlife(Long id, RestaurantsAndNightlife updatedRestaurantAndNightlife,
                                                                Region region, DineType dineType, String username) {
        Optional<RestaurantsAndNightlife> restaurantAndNightlife = restaurantsAndNightlifeRepository.findById(id);
        if (restaurantAndNightlife.isPresent()) {
            RestaurantsAndNightlife existingRestaurantAndNightlife = restaurantAndNightlife.get();
            setRestaurantAndNightlifeAttributes(updatedRestaurantAndNightlife, existingRestaurantAndNightlife);
            existingRestaurantAndNightlife.setRegion(region);
            existingRestaurantAndNightlife.setDineType(dineType);
            restaurantsAndNightlifeRepository.save(existingRestaurantAndNightlife);
            actionReportService.recordAction(username, "UPDATE", existingRestaurantAndNightlife);
            return existingRestaurantAndNightlife;
        } else {
            throw new EntityNotFoundException("Restaurant And Nightlife not found with id " + id);
        }
    }

    private void setRestaurantAndNightlifeAttributes(RestaurantsAndNightlife frontEndRestaurantAndNightlife,
                                                     RestaurantsAndNightlife databaseRestaurantAndNightlife) {
        databaseRestaurantAndNightlife.setName(frontEndRestaurantAndNightlife.getName());
        databaseRestaurantAndNightlife.setCost(frontEndRestaurantAndNightlife.getCost());
        databaseRestaurantAndNightlife.setPhone(frontEndRestaurantAndNightlife.getPhone());
        databaseRestaurantAndNightlife.setDescription(frontEndRestaurantAndNightlife.getDescription());
        databaseRestaurantAndNightlife.setImageUrl(frontEndRestaurantAndNightlife.getImageUrl());
        databaseRestaurantAndNightlife.setImageAltText(frontEndRestaurantAndNightlife.getImageAltText());
        databaseRestaurantAndNightlife.setAcceptsReservations(frontEndRestaurantAndNightlife.getAcceptsReservations());
    }
}
