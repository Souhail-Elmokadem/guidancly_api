package com.guidancly.guidancly_api.guide.mappers;


import com.guidancly.guidancly_api.guide.dao.entities.Guide;
import com.guidancly.guidancly_api.guide.dto.GuideDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GuideMapperImpl implements GuideMapper {

    private final ModelMapper modelMapper;

    @Override
    public GuideDto convertToDto(Guide guide) {
        return modelMapper.map(guide, GuideDto.class);
    }

    @Override
    public Guide convertToGuide(GuideDto guideDto) {
        return modelMapper.map(guideDto, Guide.class);
    }
}
