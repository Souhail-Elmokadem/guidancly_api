package com.guidancly.guidancly_api.location.dao.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "locations")
public class Location {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double latitude;
    private Double longitude;

    //longitude  soon
    // latitude soon
    // settings of maps soon

}
