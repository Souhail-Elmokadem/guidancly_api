package com.guidancly.guidancly_api.tour.mappers;

import com.guidancly.guidancly_api.stop.dao.entities.Stop;
import com.guidancly.guidancly_api.tour.dao.entities.Tour;
import com.guidancly.guidancly_api.tour.dto.TourBookingDTO;
import com.guidancly.guidancly_api.tour.dto.TourDTO;
import com.guidancly.guidancly_api.tour.dto.TourDtoReceive;
import com.guidancly.guidancly_api.visitor.dao.entities.Visitor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourMapperImpl implements TourMapper {

    private final ModelMapper modelMapper;

    public TourMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        // Configure custom mapping
//        TypeMap<Tour, TourDTO> typeMap = modelMapper.createTypeMap(Tour.class, TourDTO.class);
//        typeMap.addMappings(mapper -> mapper.using(ctx -> {
//            List<Visitor> visitors = (List<Visitor>) ctx.getSource();
//            return visitors.stream()
//                    .map(Visitor::getEmail)
//                    .collect(Collectors.toList());
//        }).map(Tour::getVisitors, TourDTO::setVisitorsEmails));
    }

    @Override
    public TourDTO convertToDTO(Tour tour) {
        return modelMapper.map(tour, TourDTO.class);
    }

    @Override
    public Tour covertToTour(TourDTO tourDTO) {
        return modelMapper.map(tourDTO, Tour.class);
    }
    @Override
    public Tour covertTourReceiveToTour(TourDtoReceive tourDTO) {
        Tour tour = modelMapper.map(tourDTO, Tour.class);

        tour.setStops(Collections.singletonList(modelMapper.map(tourDTO.getStops(), Stop.class)));
        System.out.println(tour);
        return  tour;
    }

    @Override
    public TourBookingDTO covertToBookingDTO(Tour tour) {

        return modelMapper.map(tour,TourBookingDTO.class);
    }
}
