package com.guidancly.guidancly_api.tour.services;

import com.guidancly.guidancly_api.location.dao.entities.Location;
import com.guidancly.guidancly_api.tour.dao.entities.Tour;
import com.guidancly.guidancly_api.tour.dto.TourBookingDTO;
import com.guidancly.guidancly_api.tour.dto.TourDTO;
import com.guidancly.guidancly_api.tour.dto.TourDtoReceive;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface TourService {

    Collection<TourDTO> getAllTours();

    Page<TourDTO> getAllToursPage(String kw, int page, int size);

    TourDTO getTour(Long TourId);

    TourDTO getTourByGuide(Long GuideId);

    TourDTO getTourByDepartLocations(Location Depart);

    List<TourBookingDTO> getTourByCurrentLocations(Double lat, Double lng);

    TourDTO createTour(TourDtoReceive tour, String token) throws IOException;

    TourDTO updateTour(TourDTO tourDTO);

    void DeleteTour(Long TourId);

}
