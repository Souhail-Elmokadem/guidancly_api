package com.guidancly.guidancly_api.tour_history.services;

import com.guidancly.guidancly_api.guide.dao.entities.Guide;
import com.guidancly.guidancly_api.tour.dao.entities.Tour;
import com.guidancly.guidancly_api.tour.dto.TourDTO;
import com.guidancly.guidancly_api.tour_history.dto.TourHistoryDTO;

import java.util.List;

public interface TourHistoryService {

    List<TourHistoryDTO> getTourHistoryByVisitor(String Email);

    List<TourHistoryDTO> getTourHistoryByGuide(String Email);

    TourHistoryDTO AddTourToHistryListForGuide(Tour tour, String Email);

    TourHistoryDTO AddTourToHistryListForVisitor(Tour tour, String Email);
}
