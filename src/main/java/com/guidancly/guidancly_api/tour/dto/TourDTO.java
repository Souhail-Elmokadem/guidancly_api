package com.guidancly.guidancly_api.tour.dto;

import com.guidancly.guidancly_api.guide.dao.entities.Guide;
import com.guidancly.guidancly_api.guide.dto.GuideDto;
import com.guidancly.guidancly_api.location.dao.entities.Location;
import com.guidancly.guidancly_api.stop.Dto.StopDTO;
import com.guidancly.guidancly_api.stop.dao.entities.Stop;
import com.guidancly.guidancly_api.visitor.dao.entities.Visitor;
import com.guidancly.guidancly_api.visitor.dto.VisitorDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourDTO {
    Long id;
    String title;
    String description;
    StopDTO depart;
    List<StopDTO> stops;
    int estimatedTime;
    Date date;
    int distance;
    GuideDto guide;
    int numberOfVisitors;
    double price;
    List<String> images;

}
