package com.guidancly.guidancly_api.guide.services;

import com.guidancly.guidancly_api.guide.dto.GuideDto;
import com.guidancly.guidancly_api.tour.dto.TourDTO;

import java.util.Collection;
import java.util.List;

public interface GuideService {

    List<GuideDto>  getAllGuides();

    Collection<TourDTO> getToursByGuide(String token);
}
