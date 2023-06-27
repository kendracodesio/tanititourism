package com.kendrareynolds.tanititourism.controller;

import com.kendrareynolds.tanititourism.entity.ThingToDo;
import com.kendrareynolds.tanititourism.repository.ThingToDoRepository;
import com.kendrareynolds.tanititourism.service.AdminThingToDoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.List;



@ExtendWith(MockitoExtension.class)
class AdminThingToDoControllerTest {

    @Mock
    private ThingToDoRepository thingToDoRepository;

    @InjectMocks
    private AdminThingToDoService adminThingToDoService;

    @Test
    void getAllThingsToDoTest() {
        //initializing two ThingToDo objects
        ThingToDo thingToDo = new ThingToDo();
        thingToDo.setName("Test Thing To Do");
        ThingToDo thingToDo1 = new ThingToDo();
        thingToDo.setName("Test Thing To Do 2");

        //adding objects to expectedList and expectedList to expectedPage
        List<ThingToDo> expectedList = List.of(thingToDo, thingToDo1);
        Page<ThingToDo> expectedPage = new PageImpl<>(expectedList);

        //simulating getting objects from repository
        Mockito.when(thingToDoRepository.findAll(Mockito.any(Pageable.class))).thenReturn(expectedPage);

        //simulating the result from the service class
        Page<ThingToDo> result = adminThingToDoService.getAllThingsToDo(1, 10);

        //testing that we get our expected result back
        Assertions.assertEquals(expectedPage, result);
    }


}
