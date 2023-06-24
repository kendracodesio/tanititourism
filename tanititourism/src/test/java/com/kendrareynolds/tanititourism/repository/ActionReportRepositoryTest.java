package com.kendrareynolds.tanititourism.repository;
import com.kendrareynolds.tanititourism.entity.ActionReport;
import com.kendrareynolds.tanititourism.entity.PlaceToStay;
import com.kendrareynolds.tanititourism.entity.ThingToDo;
import com.kendrareynolds.tanititourism.entity.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class ActionReportRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ActionReportRepository actionReportRepository;

    @Test
    @Transactional
    public void testFindTop5ByUserMostRecent() {
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("password1");
        user1.setRole(User.Role.ADMIN);
        entityManager.persist(user1);

        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword("password2");
        user2.setRole(User.Role.ADMIN);
        entityManager.persist(user2);

        ThingToDo thingToDo = new ThingToDo();
        thingToDo.setName("test1");
        entityManager.persist(thingToDo);

        PlaceToStay placeToStay = new PlaceToStay();
        placeToStay.setName("test2");
        entityManager.persist(placeToStay);

        ActionReport report1 = new ActionReport();
        report1.setUser(user1);
        report1.setThingToDo(thingToDo);
        report1.setAction(ActionReport.Action.CREATE);
        report1.setTimestamp(LocalDateTime.now());
        entityManager.persist(report1);

        ActionReport report2 = new ActionReport();
        report2.setUser(user2);
        report2.setPlaceToStay(placeToStay);
        report2.setAction(ActionReport.Action.CREATE);
        report2.setTimestamp(LocalDateTime.now());
        entityManager.persist(report2);

        //Test query
        Page<ActionReport> result = actionReportRepository.findTop5ByUserMostRecent(user1.getUsername(), PageRequest.of(0,5));

        //Assert only user1's reports are returned
        assertEquals(1, result.getNumberOfElements());
        assertEquals(user1.getUsername(), result.getContent().get(0).getUser().getUsername());
    }

}
