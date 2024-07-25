package com.guidancly.guidancly_api.tour_history.dto;

import com.guidancly.guidancly_api.guide.dao.entities.Guide;
import com.guidancly.guidancly_api.tour.dao.entities.Tour;
import com.guidancly.guidancly_api.tour.dto.TourDTO;
import com.guidancly.guidancly_api.visitor.dao.entities.Visitor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourHistoryDTO {

    private Long id;

    private String visitorEmail;

    private List<TourDTO> tours;

    private String guideEmail;
}
