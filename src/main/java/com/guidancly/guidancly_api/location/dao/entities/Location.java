package com.guidancly.guidancly_api.location.dao.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class Location {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String latitude;
    private String longitude;

    //longitude  soon
    // latitude soon
    // settings of maps soon

}
