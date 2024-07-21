package com.guidancly.guidancly_api.tour.dao.entities;


import com.guidancly.guidancly_api.guide.dao.entities.Guide;
import com.guidancly.guidancly_api.visitor.dao.entities.Visitor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Tour {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

     String title;

     String description;


     String depart;


     Date date;

     @OneToMany(mappedBy = "currentTour")
     List<Visitor> visitors;

     int numberOfVisitors;

     String estimatedFullTime;


     @ManyToOne
     Guide guide;

}
