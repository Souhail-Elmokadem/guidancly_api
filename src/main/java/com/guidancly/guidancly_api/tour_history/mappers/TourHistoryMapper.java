package com.guidancly.guidancly_api.tour_history.mappers;

import com.guidancly.guidancly_api.tour.dto.TourDTO;
import com.guidancly.guidancly_api.tour_history.dao.entities.TourHistory;
import com.guidancly.guidancly_api.tour_history.dto.TourHistoryDTO;

public interface TourHistoryMapper {

       TourHistoryDTO tourHistoryToDTO(TourHistory tourHistory);
       TourHistory tourHistoryDtoToTourHistory(TourHistoryDTO tourHistoryDTO);

}
