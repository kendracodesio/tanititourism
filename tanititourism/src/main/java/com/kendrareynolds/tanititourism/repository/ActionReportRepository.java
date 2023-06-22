package com.kendrareynolds.tanititourism.repository;

import com.kendrareynolds.tanititourism.entity.ActionReport;
import com.kendrareynolds.tanititourism.entity.PlaceToStay;
import com.kendrareynolds.tanititourism.entity.RestaurantsAndNightlife;
import com.kendrareynolds.tanititourism.entity.ThingToDo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;

public interface ActionReportRepository extends JpaRepository<ActionReport, Long> {



    @Query("SELECT ar FROM ActionReport ar WHERE " +
            "ar.thingToDo.name LIKE %:searchQuery% OR " +
            "ar.placesToStay.name LIKE %:searchQuery% OR " +
            "ar.restaurantsAndNightlife.name LIKE %:searchQuery%")
    List<ActionReport> searchByListingName(@Param("searchQuery") String searchQuery);

    List<ActionReport> findByThingToDo(ThingToDo thingToDo);
    List<ActionReport> findByPlacesToStay(PlaceToStay placeToStay);

    @Query("SELECT ar FROM ActionReport ar WHERE ar.restaurantsAndNightlife.id = :id")
    List<ActionReport> findActionReportsByRestaurantAndNightlifeId(@Param("id") Long id);



    @Query("SELECT ar FROM ActionReport ar " +
            "WHERE ar.thingToDo IS NOT NULL " +
            "OR ar.placesToStay IS NOT NULL " +
            "OR ar.restaurantsAndNightlife IS NOT NULL " +
            "ORDER BY ar.timestamp DESC")
    Page<ActionReport> findAllWithListingOrderedByTimestampDesc(Pageable pageable);


    @Query("SELECT ar FROM ActionReport ar " +
            "WHERE ar.thingToDo IS NOT NULL " +
            "OR ar.placesToStay IS NOT NULL " +
            "OR ar.restaurantsAndNightlife IS NOT NULL " +
            "AND ar.user.username = :username " +
            "ORDER BY ar.timestamp DESC")
    List<ActionReport> findTop5ByUser_UsernameWithListingOrderByTimestampDesc(String username);


    List<ActionReport> findByTimestampBetweenOrderByTimestampDesc(OffsetDateTime start, OffsetDateTime end);

    List<ActionReport> findByUserIdAndTimestampBetweenOrderByTimestampDesc(Long userId, OffsetDateTime start, OffsetDateTime end);

}
