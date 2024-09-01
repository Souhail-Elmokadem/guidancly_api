package com.guidancly.guidancly_api.tour.mappers;

import com.guidancly.guidancly_api.tour.dao.entities.Tour;
import com.guidancly.guidancly_api.tour.dto.TourBookingDTO;
import com.guidancly.guidancly_api.tour.dto.TourDTO;
import com.guidancly.guidancly_api.tour.dto.TourDtoReceive;

public interface TourMapper {

    TourDTO convertToDTO(Tour tour);

    Tour covertToTour(TourDTO tourDTO);

    Tour covertTourReceiveToTour(TourDtoReceive tourDTO);
    TourBookingDTO covertToBookingDTO(Tour tour);
}
