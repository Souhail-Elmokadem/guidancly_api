package com.guidancly.guidancly_api.tour.dto;

import com.guidancly.guidancly_api.guide.dao.entities.Guide;
import com.guidancly.guidancly_api.stop.dao.entities.Stop;
import com.guidancly.guidancly_api.visitor.dao.entities.Visitor;
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
    String depart;
    Date date;
    List<String> visitorsEmail;
    int numberOfVisitors;
    String estimatedFullTime;
    List<Stop> stops;
    String guideEmail;
}
