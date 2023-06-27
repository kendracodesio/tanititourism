package com.kendrareynolds.tanititourism.service;

import com.kendrareynolds.tanititourism.entity.*;
import com.kendrareynolds.tanititourism.repository.ActionReportRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ActionReportServiceTest {

    @Mock
    private ActionReportRepository actionReportRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private ActionReportService actionReportService;

    private String username;
    private User mockUser;

    @BeforeEach
    void setUp() {
        username = "testUser";
        mockUser = new User();
        mockUser.setUsername(username);
    }


    @Test
    void recordAction_DoType() {
        //creating the mock thing to do listing
        ThingToDo mockThingToDo = new ThingToDo();

        //simulating getting the user from the userService
        Mockito.when(userService.findUserByUsername(username)).thenReturn(mockUser);

        //testing our service method that adds an ActionReport instance
        actionReportService.recordAction(username, "CREATE", mockThingToDo);

        //The ArgumentCaptor is used to capture the argument passed to actionReportRepository.save()
        ArgumentCaptor<ActionReport> actionReportCaptor = ArgumentCaptor.forClass(ActionReport.class);

        //Verify that the save method was called and capture the argument that was passed
        Mockito.verify(actionReportRepository).save(actionReportCaptor.capture());

        //Validating the results from the test
        ActionReport savedReport = actionReportCaptor.getValue();
        Assertions.assertEquals(mockUser, savedReport.getUser());
        Assertions.assertEquals(mockThingToDo, savedReport.getThingToDo());
        Assertions.assertEquals(ActionReport.Action.CREATE, savedReport.getAction());
        Assertions.assertNull(savedReport.getPlaceToStay());
        Assertions.assertNull(savedReport.getRestaurantsAndNightlife());

    }

    @Test
    void recordAction_StayType() {
        //creating the mock place to stay listing
        PlaceToStay mockPlaceToStay = new PlaceToStay();

        //simulating getting the user from the userService
        Mockito.when(userService.findUserByUsername(username)).thenReturn(mockUser);

        //testing our service method that adds an ActionReport instance
        actionReportService.recordAction(username, "CREATE", mockPlaceToStay);

        //The ArgumentCaptor is used to capture the argument passed to actionReportRepository.save()
        ArgumentCaptor<ActionReport> actionReportCaptor = ArgumentCaptor.forClass(ActionReport.class);

        //Verify that the save method was called and capture the argument that was passed
        Mockito.verify(actionReportRepository).save(actionReportCaptor.capture());

        //Validating the results from the test
        ActionReport savedReport = actionReportCaptor.getValue();
        Assertions.assertEquals(mockUser, savedReport.getUser());
        Assertions.assertEquals(mockPlaceToStay, savedReport.getPlaceToStay());
        Assertions.assertEquals(ActionReport.Action.CREATE, savedReport.getAction());
        Assertions.assertNull(savedReport.getThingToDo());
        Assertions.assertNull(savedReport.getRestaurantsAndNightlife());

    }

    @Test
    void recordAction_DineType() {
        //creating the mock restaurants and nightlife listing
        RestaurantsAndNightlife mockRestaurantsAndNightlife = new RestaurantsAndNightlife();

        //simulating getting the user from the userService
        Mockito.when(userService.findUserByUsername(username)).thenReturn(mockUser);

        //testing our service method that adds an ActionReport instance
        actionReportService.recordAction(username, "CREATE", mockRestaurantsAndNightlife);

        //The ArgumentCaptor is used to capture the argument passed to actionReportRepository.save()
        ArgumentCaptor<ActionReport> actionReportCaptor = ArgumentCaptor.forClass(ActionReport.class);

        //Verify that the save method was called and capture the argument that was passed
        Mockito.verify(actionReportRepository).save(actionReportCaptor.capture());

        //Validating the results from the test
        ActionReport savedReport = actionReportCaptor.getValue();
        Assertions.assertEquals(mockUser, savedReport.getUser());
        Assertions.assertEquals(mockRestaurantsAndNightlife, savedReport.getRestaurantsAndNightlife());
        Assertions.assertEquals(ActionReport.Action.CREATE, savedReport.getAction());
        Assertions.assertNull(savedReport.getThingToDo());
        Assertions.assertNull(savedReport.getPlaceToStay());

    }

    @Test
    void testGetUserRecentActivity() {
        //create mock listing
        ThingToDo thingToDo = new ThingToDo();

        //initializing the action report with the mock user and listing
        ActionReport report1 = new ActionReport();
        report1.setUser(mockUser);
        report1.setThingToDo(thingToDo);
        report1.setAction(ActionReport.Action.CREATE);

       //preparing a list of reports by user1 -- what the database query should return
        List<ActionReport> reports = Collections.singletonList(report1);
        Page<ActionReport> page = new PageImpl<>(reports);

        //simulating the repository method to fetch the data from the database
        when(actionReportRepository.findTop5ByUserMostRecent("username", PageRequest.of(0, 5))).thenReturn(page);

        //testing our service method to get the recent activity for user1
        List<ActionReport> result = actionReportService.getUserRecentActivity("username");

        //Validating the results from the test
        assertEquals(1, result.size());
        assertEquals(report1, result.get(0));

    }


    @Test
    void getAllActionReportsWithListings() {
        //create mock listings
        PlaceToStay placeToStay = new PlaceToStay();
        ThingToDo thingToDo = new ThingToDo();

        //initializing action report 1 with the mock user creating a Place To Stay listing
        ActionReport report1 = new ActionReport();
        report1.setUser(mockUser);
        report1.setPlaceToStay(placeToStay);
        report1.setAction(ActionReport.Action.CREATE);
        report1.setTimestamp(LocalDateTime.now());

        String username2 = "testUser2";
        User mockUser2 = new User();
        mockUser2.setUsername(username2);

        //Initializing action report 2 with the mock user updating a Thing To Do Listing
        ActionReport report2 = new ActionReport();
        report2.setUser(mockUser2);
        report2.setAction(ActionReport.Action.UPDATE);
        report2.setThingToDo(thingToDo);
        report1.setTimestamp(LocalDateTime.now());

        //preparing a list of reports from all users -- what the database query should return
        List<ActionReport> reports = List.of(report1, report2);
        Page<ActionReport> page = new PageImpl<>(reports);

        //simulating the repository method to fetch the data from the database
        when(actionReportRepository.findAllWithListingOrderedByTimestampDesc(PageRequest.of(0, 20))).thenReturn(page);

        //testing our service method to get all reports from all users
        Page<ActionReport> result = actionReportService.getAllActionReportsWithListings(1, 20);

        //Validating the results from the test
        assertEquals(2, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
    }
}
