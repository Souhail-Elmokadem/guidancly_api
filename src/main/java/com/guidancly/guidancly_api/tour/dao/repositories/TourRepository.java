package com.guidancly.guidancly_api.tour.dao.repositories;

import com.guidancly.guidancly_api.tour.dao.entities.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TourRepository extends JpaRepository<Tour, Long> {

    Optional<Tour> findAllByGuideId(Long GuideId);

    Optional<Tour> findAllByStopsId(Long StopId);

}
