package org.tdb.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DashboardRepository extends JpaRepository<Dashboard, Long> {

    Optional<Dashboard> findByName(String name);

    @Query("SELECT d.account FROM Dashboard d WHERE d.id = :dashboardId")
    Account getOwningAccount(@Param("dashboardId")Long dashboardId);
}
