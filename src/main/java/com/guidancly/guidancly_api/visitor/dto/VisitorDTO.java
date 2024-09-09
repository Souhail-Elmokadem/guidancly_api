package com.guidancly.guidancly_api.visitor.dto;

import com.guidancly.guidancly_api.common.dao.entities.Preferences;
import com.guidancly.guidancly_api.tour.dao.entities.Tour;
import com.guidancly.guidancly_api.tour.dto.TourDTO;
import com.guidancly.guidancly_api.user.dto.UserDto;
import com.guidancly.guidancly_api.user.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@NoArgsConstructor
@Getter
@Setter
public class VisitorDTO extends UserDto {

    private Preferences preferences;


    private TourDTO currentTour;



    public VisitorDTO(String firstName, String lastName, String email, String number, String avatar, Role role, Date createdAt, Date updatedAt, Preferences preferences, TourDTO currentTour) {
        super(firstName, lastName, email, number, avatar, role, createdAt, updatedAt);
        this.preferences = preferences;
        this.currentTour = currentTour;
    }
}
