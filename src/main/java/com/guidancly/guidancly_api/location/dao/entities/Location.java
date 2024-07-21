package com.guidancly.guidancly_api.location.dao.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Location {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
    private String description;


    //longitude  soon
    // latitude soon
    // settings of maps soon
}
