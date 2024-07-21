package com.guidancly.guidancly_api.guide.dao.entities;

import com.guidancly.guidancly_api.guide.enums.GuideType;
import com.guidancly.guidancly_api.location.dao.entities.Location;
import com.guidancly.guidancly_api.user.dao.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Guide extends User
{
    @Enumerated(EnumType.STRING)
    GuideType guideType;

    @OneToOne
    Location location;
}