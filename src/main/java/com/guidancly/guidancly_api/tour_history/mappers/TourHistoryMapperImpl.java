package com.guidancly.guidancly_api.tour_history.mappers;

import com.guidancly.guidancly_api.tour_history.dao.entities.TourHistory;
import com.guidancly.guidancly_api.tour_history.dto.TourHistoryDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TourHistoryMapperImpl implements TourHistoryMapper{


    private final ModelMapper modelMapper;



    @Override
    public TourHistoryDTO tourHistoryToDTO(TourHistory tourHistory) {
        return modelMapper.map(tourHistory , TourHistoryDTO.class);
    }

    @Override
    public TourHistory tourHistoryDtoToTourHistory(TourHistoryDTO tourHistoryDTO) {
        return modelMapper.map(tourHistoryDTO, TourHistory.class);
    }
}
