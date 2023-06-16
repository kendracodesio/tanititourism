package com.kendrareynolds.tanititourism.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ThingToDoRepositoryTest {

    @Autowired
    private ThingToDoRepository thingToDoRepository;

    @Test
    void findByStatusAndDeletedAtIsNullTest() {

    }
}
