package com.guidancly.guidancly_api.stop.Dto;

import com.guidancly.guidancly_api.location.dao.entities.Location;
import com.guidancly.guidancly_api.tour.dao.entities.Tour;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StopDTO {
    private Long id;
    String name;
    Location location;
    List<Long> toursId;
    String description;
}
