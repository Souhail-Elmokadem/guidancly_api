package com.guidancly.guidancly_api.stop.mappers;

import com.guidancly.guidancly_api.common.utils.ModelMapperConfig;
import com.guidancly.guidancly_api.stop.Dto.StopDTO;
import com.guidancly.guidancly_api.stop.dao.entities.Stop;
import com.guidancly.guidancly_api.tour.dao.entities.Tour;
import com.guidancly.guidancly_api.tour.dto.TourDTO;
import com.guidancly.guidancly_api.visitor.dao.entities.Visitor;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class StopMapperImpl implements StopMapper{


    private final ModelMapper modelMapper;


    public StopMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        // Configure custom mapping
        TypeMap<Stop, StopDTO> typeMap = modelMapper.createTypeMap(Stop.class, StopDTO.class);
        typeMap.addMappings(mapper -> mapper.using(ctx -> {
            List<Tour> tours = (List<Tour>) ctx.getSource();
            return tours.stream()
                    .map(Tour::getId)
                    .collect(Collectors.toList());
        }).map(Stop::getTours, StopDTO::setToursId));
    }


    @Override
    public StopDTO fromStopToStopDTO(Stop stop) {
        return modelMapper.map(stop, StopDTO.class);
    }

    @Override
    public Stop fromDTOtoStop(StopDTO stopDTO) {
        return modelMapper.map(stopDTO, Stop.class);
    }
}
