package com.guidancly.guidancly_api.visitor.dao.entities;
import com.guidancly.guidancly_api.common.dao.entities.Preferences;
import com.guidancly.guidancly_api.user.dao.entities.User;
import com.guidancly.guidancly_api.tour.dao.entities.Tour;

import com.guidancly.guidancly_api.user.enums.Role;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Visitor extends User{
    @ManyToOne
    private Preferences preferences;

    @ManyToOne
    private Tour currentTour;


    @OneToMany
    private List<Tour> tourListVisited;



}
