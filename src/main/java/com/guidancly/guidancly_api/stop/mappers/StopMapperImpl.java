package com.guidancly.guidancly_api.stop.mappers;

import com.guidancly.guidancly_api.common.utils.ModelMapperConfig;
import com.guidancly.guidancly_api.stop.Dto.StopDTO;
import com.guidancly.guidancly_api.stop.dao.entities.Stop;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class StopMapperImpl implements StopMapper{


    private final ModelMapper modelMapper;

    @Override
    public StopDTO fromStopToStopDTO(Stop stop) {
        return modelMapper.map(stop, StopDTO.class);
    }

    @Override
    public Stop fromDTOtoStop(StopDTO stopDTO) {
        return modelMapper.map(stopDTO, Stop.class);
    }
}
