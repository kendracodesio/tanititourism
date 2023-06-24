package com.kendrareynolds.tanititourism.repository;

import com.kendrareynolds.tanititourism.entity.DoType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoTypeRepository extends JpaRepository<DoType, Long> {

    Optional<DoType> findByTypeName (String typeName);

}
