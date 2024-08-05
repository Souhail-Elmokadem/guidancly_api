package com.guidancly.guidancly_api.tour.services;

import com.guidancly.guidancly_api.location.dao.entities.Location;
import com.guidancly.guidancly_api.tour.dao.entities.Tour;
import com.guidancly.guidancly_api.tour.dto.TourDTO;
import com.guidancly.guidancly_api.tour.dto.TourDtoReceive;

import java.util.Collection;
import java.util.List;

public interface TourService {

    Collection<TourDTO> getAllTours();

    TourDTO getTour(Long TourId);

    TourDTO getTourByGuide(Long GuideId);

    TourDTO getTourByDepartLocations(Location Depart);

    TourDTO createTour(TourDtoReceive tour);

    TourDTO updateTour(TourDTO tourDTO);

    void DeleteTour(Long TourId);

}
