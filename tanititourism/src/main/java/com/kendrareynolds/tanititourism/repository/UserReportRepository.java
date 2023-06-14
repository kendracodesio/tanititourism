package com.kendrareynolds.tanititourism.repository;

import com.kendrareynolds.tanititourism.entity.UserReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReportRepository extends JpaRepository<UserReport, Long> {
}
