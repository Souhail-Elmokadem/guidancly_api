package com.guidancly.guidancly_api.stop.services;

import com.guidancly.guidancly_api.stop.Dto.StopDTO;
import com.guidancly.guidancly_api.stop.dao.repositories.StopRepository;
import com.guidancly.guidancly_api.stop.mappers.StopMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StopServiceImpl implements StopService{

    private final StopRepository stopRepository;
    private final StopMapper stopMapper;



    @Override
    public Collection<StopDTO> getAllStops() {
        return stopRepository.findAll()
                .stream()
                .map(stop -> stopMapper.fromStopToStopDTO(stop))
                .collect(Collectors.toList());
    }
}
