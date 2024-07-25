package com.guidancly.guidancly_api.guide.dto;

import com.guidancly.guidancly_api.guide.enums.GuideType;
import com.guidancly.guidancly_api.location.dao.entities.Location;
import com.guidancly.guidancly_api.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuideDto extends UserDto {

    GuideType guideType;
    Location location;

}
