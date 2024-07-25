package com.guidancly.guidancly_api.tour.dto;

import com.guidancly.guidancly_api.guide.dao.entities.Guide;
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
    String depart;
    Date date;
    List<String> visitorsEmails;
    int numberOfVisitors;
    String estimatedFullTime;
    List<StopDTO> stops;
    String guideEmail;
}
