package com.guidancly.guidancly_api.guide.services;


import com.guidancly.guidancly_api.guide.dao.repositories.GuideRepository;
import com.guidancly.guidancly_api.guide.dto.GuideDto;
import com.guidancly.guidancly_api.guide.mappers.GuideMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuideServiceImpl implements GuideService {

    private final GuideRepository guideRepository;
    private final GuideMapper guideMapper;


    @Override
    public List<GuideDto> getAllGuides() {
        return guideRepository.findAll().stream().map(guide -> guideMapper.convertToDto(guide)).collect(Collectors.toList());
    }
}
