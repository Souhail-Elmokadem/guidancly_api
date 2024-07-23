package com.guidancly.guidancly_api.tour.mappers;

import com.guidancly.guidancly_api.tour.dao.entities.Tour;
import com.guidancly.guidancly_api.tour.dto.TourDTO;

public interface TourMapper {

    TourDTO convertToDTO(Tour tour);

    Tour covertToTour(TourDTO tourDTO);
}
