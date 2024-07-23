package com.guidancly.guidancly_api.tour.dao.repository;

import com.guidancly.guidancly_api.tour.dao.entities.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.OptionalInt;

public interface TourRepository extends JpaRepository<Tour, Long> {

    Optional<Tour> findAllByGuideId(Long GuideId);

    Optional<Tour> findAllByStopId(Long StopId);

}
