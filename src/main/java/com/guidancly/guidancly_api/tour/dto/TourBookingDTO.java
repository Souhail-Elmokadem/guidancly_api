package com.guidancly.guidancly_api.tour.dto;

import com.guidancly.guidancly_api.guide.dao.entities.Guide;
import com.guidancly.guidancly_api.guide.dto.GuideDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import com.guidancly.guidancly_api.stop.Dto.StopDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourBookingDTO {
    Long id;
    String title;
    String description;
    StopDTO depart;
    int estimatedTime;
    int distance;
    List<StopDTO> stops;
    GuideDto guide;
    double price;
    List<String> images;
}
