package com.kendrareynolds.tanititourism.repository;

import com.kendrareynolds.tanititourism.entity.ActionReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface ActionReportRepository extends JpaRepository<ActionReport, Long> {

    List<ActionReport> findTop10ByUserIdOrderByTimestampDesc(Long userId);

    List<ActionReport> findByTimestampBetweenOrderByTimestampDesc(OffsetDateTime start, OffsetDateTime end);

    List<ActionReport> findByUserIdAndTimestampBetweenOrderByTimestampDesc(Long userId, OffsetDateTime start, OffsetDateTime end);
}
