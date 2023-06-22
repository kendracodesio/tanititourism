package com.kendrareynolds.tanititourism.repository;

import com.kendrareynolds.tanititourism.entity.ActionReport;
import com.kendrareynolds.tanititourism.entity.ThingToDo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface ActionReportRepository extends JpaRepository<ActionReport, Long> {

    Page<ActionReport> findAll(Pageable pageable);

    List<ActionReport> findTop5ByUser_UsernameOrderByTimestampDesc(String username);

    List<ActionReport> findByTimestampBetweenOrderByTimestampDesc(OffsetDateTime start, OffsetDateTime end);

    List<ActionReport> findByUserIdAndTimestampBetweenOrderByTimestampDesc(Long userId, OffsetDateTime start, OffsetDateTime end);
}
