package com.guidancly.guidancly_api.tour.dto;

import com.guidancly.guidancly_api.guide.dao.entities.Guide;
import com.guidancly.guidancly_api.stop.dao.entities.Stop;
import com.guidancly.guidancly_api.visitor.dao.entities.Visitor;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

public class TourDTO {
    private Long id;
    String title;
    String description;
    String depart;
    Date date;
    List<Visitor> visitors;
    int numberOfVisitors;
    String estimatedFullTime;
    List<Stop> stops;
    Guide guide;
}
