package com.guidancly.guidancly_api.tour.dto;

import com.guidancly.guidancly_api.stop.Dto.StopDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourDtoReceive {
    String title;
    String description;
    StopDTO depart;
    List<StopDTO> stops;

}
