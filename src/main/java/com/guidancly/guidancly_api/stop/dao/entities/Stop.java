package com.guidancly.guidancly_api.stop.dao.entities;


import com.guidancly.guidancly_api.location.dao.entities.Location;
import com.guidancly.guidancly_api.tour.dao.entities.Tour;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Stop {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String name;

    @OneToOne
    Location location;

    @ManyToMany
    List<Tour> tours;
    String description;


}
