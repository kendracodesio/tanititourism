package com.kendrareynolds.tanititourism.repository;


import com.kendrareynolds.tanititourism.entity.StayType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StayTypeRepository extends JpaRepository<StayType, Long> {

    Optional<StayType> findByTypeName (String typeName);

}
