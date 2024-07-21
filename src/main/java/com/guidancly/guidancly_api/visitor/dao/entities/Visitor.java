package com.guidancly.guidancly_api.visitor.dao.entities;
import com.guidancly.guidancly_api.user.dao.entities.User;
import com.guidancly.guidancly_api.tour.dao.entities.Tour;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Visitor extends User{
    private String preferences;

    @ManyToOne
    private Tour currentTour;

}
