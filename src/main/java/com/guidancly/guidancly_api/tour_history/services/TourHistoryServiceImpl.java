package com.guidancly.guidancly_api.tour_history.services;

import com.guidancly.guidancly_api.tour.dao.entities.Tour;
import com.guidancly.guidancly_api.tour.dto.TourDTO;
import com.guidancly.guidancly_api.tour_history.dao.repositories.TourHistoryRepository;
import com.guidancly.guidancly_api.tour_history.dto.TourHistoryDTO;
import com.guidancly.guidancly_api.tour_history.mappers.TourHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TourHistoryServiceImpl implements TourHistoryService{

    private final TourHistoryRepository tourHistoryRepository;
    private final TourHistoryMapper tourHistoryMapper;


    @Override
    public List<TourHistoryDTO> getTourHistoryByVisitor(String Email) {
        return tourHistoryRepository.findAllByVisitorEmail(Email).stream().map(tourHistory -> tourHistoryMapper.tourHistoryToDTO(tourHistory)).collect(Collectors.toList());
    }

    @Override
    public List<TourHistoryDTO> getTourHistoryByGuide(String Email) {
       return tourHistoryRepository.findAllByGuideEmail(Email).stream().map(tourHistory -> tourHistoryMapper.tourHistoryToDTO(tourHistory)).collect(Collectors.toList());

    }

    @Override
    public TourHistoryDTO AddTourToHistryListForGuide(Tour tour, String Email) {
        return null;
    }

    @Override
    public TourHistoryDTO AddTourToHistryListForVisitor(Tour tour, String Email) {
        return null;
    }
}
