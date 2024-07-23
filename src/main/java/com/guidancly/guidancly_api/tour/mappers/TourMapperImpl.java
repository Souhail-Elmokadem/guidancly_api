package com.guidancly.guidancly_api.tour.mappers;


import com.guidancly.guidancly_api.tour.dao.entities.Tour;
import com.guidancly.guidancly_api.tour.dto.TourDTO;
import com.guidancly.guidancly_api.visitor.dao.entities.Visitor;
import com.guidancly.guidancly_api.visitor.dto.VisitorDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TourMapperImpl implements TourMapper {


    private final ModelMapper modelMapper;


    @Override
    public TourDTO convertToDTO(Tour tour) {
        return modelMapper.map(tour , TourDTO.class);
    }

    @Override
    public Tour covertToTour(TourDTO tourDTO) {
        return modelMapper.map(tourDTO , Tour.class);
    }
}
