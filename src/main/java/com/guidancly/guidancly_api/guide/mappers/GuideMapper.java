package com.guidancly.guidancly_api.guide.mappers;

import com.guidancly.guidancly_api.guide.dao.entities.Guide;
import com.guidancly.guidancly_api.guide.dto.GuideDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;



public interface GuideMapper {

     GuideDto convertToDto(Guide guide);
     Guide convertToGuide(GuideDto guideDto);
}
