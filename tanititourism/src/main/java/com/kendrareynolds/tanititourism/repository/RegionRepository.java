package com.kendrareynolds.tanititourism.repository;

import com.kendrareynolds.tanititourism.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {

    Optional<Region> findByName (String name);

}
