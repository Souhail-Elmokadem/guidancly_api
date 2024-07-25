package com.guidancly.guidancly_api.tour_history.dao.repositories;

import com.guidancly.guidancly_api.tour_history.dao.entities.TourHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TourHistoryRepository extends JpaRepository<TourHistory,Long> {
    Optional<TourHistory> findAllByVisitorEmail(String email);
    Optional<TourHistory> findAllByGuideEmail(String email);
}
