package com.guidancly.guidancly_api.guide.services;


import com.guidancly.guidancly_api.common.services.BaseImpl;
import com.guidancly.guidancly_api.guide.dao.entities.Guide;
import com.guidancly.guidancly_api.guide.dao.repositories.GuideRepository;
import com.guidancly.guidancly_api.guide.dto.GuideDto;
import com.guidancly.guidancly_api.guide.mappers.GuideMapper;
import com.guidancly.guidancly_api.tour.dao.repositories.TourRepository;
import com.guidancly.guidancly_api.tour.dto.TourDTO;
import com.guidancly.guidancly_api.tour.mappers.TourMapper;
import com.guidancly.guidancly_api.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuideServiceImpl implements GuideService {

    private final GuideRepository guideRepository;
    private final GuideMapper guideMapper;
    private final BaseImpl base;
    private final TourRepository tourRepository;
    private final TourMapper tourMapper;


    @Override
    public List<GuideDto> getAllGuides() {
        return guideRepository.findAll().stream().map(guide -> guideMapper.convertToDto(guide)).collect(Collectors.toList());
    }

    @Override
    public Collection<TourDTO> getToursByGuide(String token) {
        UserDto userDto = base.getUserByToken(token);
        Guide guide = guideRepository.findByEmailOrNumber(userDto.getEmail(), userDto.getNumber());
        Collection<TourDTO> tourDTO = tourRepository.findToursByGuideId(guide.getId()).stream().map(t->tourMapper.convertToDTO(t)).collect(Collectors.toList());
        return tourDTO;
    }

}
