package com.guidancly.guidancly_api.tour.dao.repositories;

import com.guidancly.guidancly_api.tour.dao.entities.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TourRepository extends JpaRepository<Tour, Long> {

    Optional<Tour> findAllByGuideId(Long GuideId);

    Optional<Tour> findAllByStopsId(Long StopId);


    @Query(value = "SELECT t.* FROM tours t " +
            "JOIN stops d ON t.depart_id = d.id " +
            "JOIN locations l ON d.location_id = l.id " +
            "WHERE ST_Distance_Sphere(point(:longitude, :latitude), point(l.longitude, l.latitude)) < :distance",
            nativeQuery = true)
    List<Tour> findToursNearby(@Param("latitude") double latitude,
                               @Param("longitude") double longitude,
                               @Param("distance") double distance);

    Page<Tour> findByTitleContaining(String kw, PageRequest pageRequest);

}
