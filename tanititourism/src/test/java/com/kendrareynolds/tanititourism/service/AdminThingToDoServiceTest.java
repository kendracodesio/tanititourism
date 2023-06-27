package com.kendrareynolds.tanititourism.service;

import com.kendrareynolds.tanititourism.entity.DoType;
import com.kendrareynolds.tanititourism.entity.Region;
import com.kendrareynolds.tanititourism.entity.ThingToDo;
import com.kendrareynolds.tanititourism.entity.User;
import com.kendrareynolds.tanititourism.exception.DuplicateListingException;
import com.kendrareynolds.tanititourism.repository.ThingToDoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AdminThingToDoServiceTest {

    @Mock
    private ActionReportService actionReportService;

    @Mock
    private ThingToDoRepository thingToDoRepository;

    @InjectMocks
    private AdminThingToDoService adminThingToDoService;

    @Test
    void updateThingToDo_WhenThingToDoNotFound_ShouldThrowException() {
        //creating a mock id not associated with a Thing To Do
        Long id = 20L;

        //simulating returning empty optional
        Mockito.when(thingToDoRepository.findById(id)).thenReturn(Optional.empty());

        //attempting to update a non-existing ThingToDo and expecting an EntityNotFoundException
        assertThrows(EntityNotFoundException.class, () -> {
            adminThingToDoService.updateThingToDo(id, new ThingToDo(), new Region(), new HashSet<>(), "username");
        });
    }

    @Test
    void updateThingToDo_WhenNameDuplicatesExisting_ShouldThrowException() {
        //set up existing ThingToDo
        Long id = 1L;
        ThingToDo existingThingToDo = new ThingToDo();
        existingThingToDo.setName("name");

        //set up updated ThingToDo with an updated name
        ThingToDo updatedThingToDo = new ThingToDo();
        updatedThingToDo.setName("updatedNameDuplicate");

        //set up another ThingToDo with the same name as the updated listing
        ThingToDo duplicateThingToDo = new ThingToDo();
        duplicateThingToDo.setName("updatedNameDuplicate");

        //Simulate responses from the repository
        Mockito.when(thingToDoRepository.findById(id)).thenReturn(Optional.of(existingThingToDo));
        Mockito.when(thingToDoRepository.findByName(updatedThingToDo.getName())).thenReturn(Optional.of(duplicateThingToDo));

        //Call updateThingToDo and check that it throws a DuplicateListingException
        assertThrows(DuplicateListingException.class, () -> {
            adminThingToDoService.updateThingToDo(id, updatedThingToDo, new Region(), new HashSet<>(), "username");
        });

    }

    @Test
    void updateThingToDo_WhenSuccessful_ShouldUpdateAttributesAndRecordAction() {
        Long id = 1L;
        ThingToDo existingThingToDo = new ThingToDo();
        existingThingToDo.setName("name");
        ThingToDo updatedThingToDo = new ThingToDo();
        updatedThingToDo.setName("updatedName");
        Set<DoType> doTypes = new HashSet<>();
        DoType doType = new DoType();
        doType.setTypeName("doType");
        doTypes.add(doType);
        Region region = new Region();
        region.setName("region");

        //Simulate responses form the repository
        Mockito.when(thingToDoRepository.findById(id)).thenReturn(Optional.of(existingThingToDo));
        Mockito.when(thingToDoRepository.findByName(updatedThingToDo.getName())).thenReturn(Optional.empty());
        //Use ArgumentCaptors to capture the arguments passed to save and recordAction
        ArgumentCaptor<ThingToDo> thingToDoCaptor = ArgumentCaptor.forClass(ThingToDo.class);
        ArgumentCaptor<String> actionCaptor = ArgumentCaptor.forClass(String.class);

        //Call updateThingToDo
        adminThingToDoService.updateThingToDo(id, updatedThingToDo, region, doTypes, "username");
        //Verify that save and recordAction were called with the correct arguments
        Mockito.verify(thingToDoRepository).save(thingToDoCaptor.capture());
        ThingToDo savedThingToDo = thingToDoCaptor.getValue();
        assertEquals(updatedThingToDo.getName(), savedThingToDo.getName());
        assertEquals(region.getName(), savedThingToDo.getRegion().getName());
        assertEquals(doTypes, savedThingToDo.getDoTypes());

        Mockito.verify(actionReportService).recordAction(Mockito.eq("username"), actionCaptor.capture(), Mockito.eq(savedThingToDo));
        assertEquals("UPDATE", actionCaptor.getValue());

    }
}