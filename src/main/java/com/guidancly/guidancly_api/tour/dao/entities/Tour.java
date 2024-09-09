package com.guidancly.guidancly_api.tour.dao.entities;


import com.guidancly.guidancly_api.guide.dao.entities.Guide;
import com.guidancly.guidancly_api.location.dao.entities.Location;
import com.guidancly.guidancly_api.stop.dao.entities.Stop;
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
@Table(name = "tours")
public class Tour {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;
     String title; // tourName
     String description;
     //Location depart;
     Date date;
     @OneToMany(mappedBy = "currentTour")
     List<Visitor> visitors;
     int numberOfVisitors;
     double price;
     @ElementCollection
     List<String> images;

 @Override
 public String toString() {
  return "Tour{" +
          "id=" + id +
          ", title='" + title + '\'' +
          ", description='" + description + '\'' +
          ", depart=" + depart +
          ", stops=" + stops +
          ", guide=" + guide +
          '}';
 }
    @ManyToOne
     private Stop depart; // started

     int estimatedTime;
     int distance;

     @ManyToMany
     List<Stop> stops; // waypoints

     @ManyToOne
     Guide guide;
}
