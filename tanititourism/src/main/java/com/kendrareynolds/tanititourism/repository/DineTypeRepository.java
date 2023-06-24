package com.kendrareynolds.tanititourism.repository;

import com.kendrareynolds.tanititourism.entity.DineType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DineTypeRepository extends JpaRepository<DineType, Long> {

    Optional<DineType> findByTypeName (String typeName);
}
