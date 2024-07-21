package com.guidancly.guidancly_api.stop.dao.entities;


import com.guidancly.guidancly_api.location.dao.entities.Location;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    String description;


}
