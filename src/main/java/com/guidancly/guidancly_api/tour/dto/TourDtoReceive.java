package com.guidancly.guidancly_api.tour.dto;

import com.guidancly.guidancly_api.stop.Dto.StopDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourDtoReceive {
    Long id;
    String title;
    String description;
    StopDTO depart;
    int estimatedTime;
    int distance;
    List<StopDTO> stops;
    private double price;
    private List<String> images;

}
