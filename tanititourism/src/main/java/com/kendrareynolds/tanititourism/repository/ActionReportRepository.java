package com.kendrareynolds.tanititourism.repository;

import com.kendrareynolds.tanititourism.entity.ActionReport;
import com.kendrareynolds.tanititourism.entity.PlaceToStay;
import com.kendrareynolds.tanititourism.entity.ThingToDo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;



public interface ActionReportRepository extends JpaRepository<ActionReport, Long> {


    @Query("SELECT ar FROM ActionReport ar " +
            "LEFT JOIN ar.thingToDo ttd " +
            "LEFT JOIN ar.placeToStay pts " +
            "LEFT JOIN ar.restaurantsAndNightlife ran " +
            "WHERE (" +
            "ttd IS NOT NULL AND lower(ttd.name) LIKE lower(concat('%', :searchQuery, '%')) OR " +
            "pts IS NOT NULL AND lower(pts.name) LIKE lower(concat('%', :searchQuery, '%')) OR " +
            "ran IS NOT NULL AND lower(ran.name) LIKE lower(concat('%', :searchQuery, '%'))" +
            ")")
    List<ActionReport> searchByListingName(@Param("searchQuery") String searchQuery);


    List<ActionReport> findByThingToDo(ThingToDo thingToDo);

    List<ActionReport> findByPlaceToStay(PlaceToStay placeToStay);

    @Query("SELECT ar FROM ActionReport ar WHERE ar.restaurantsAndNightlife.id = :id")
    List<ActionReport> findActionReportsByRestaurantAndNightlifeId(@Param("id") Long id);


    @Query("SELECT ar FROM ActionReport ar " +
            "WHERE ar.thingToDo IS NOT NULL " +
            "OR ar.placeToStay IS NOT NULL " +
            "OR ar.restaurantsAndNightlife IS NOT NULL " +
            "ORDER BY ar.timestamp DESC")
    Page<ActionReport> findAllWithListingOrderedByTimestampDesc(Pageable pageable);


    @Query(
            value = "SELECT ar FROM ActionReport ar " +
                    "WHERE (ar.thingToDo IS NOT NULL " +
                    "OR ar.placeToStay IS NOT NULL " +
                    "OR ar.restaurantsAndNightlife IS NOT NULL )" +
                    "AND ar.user.username = :username " +
                    "ORDER BY ar.timestamp DESC",
            countQuery = "SELECT count(*) FROM ActionReport ar " +
                    "WHERE (ar.thingToDo IS NOT NULL " +
                    "OR ar.placeToStay IS NOT NULL " +
                    "OR ar.restaurantsAndNightlife IS NOT NULL )" +
                    "AND ar.user.username = :username")
    Page<ActionReport> findTop5ByUserMostRecent(String username, Pageable pageable);


}
