package com.guidancly.guidancly_api.stop.mappers;

import com.guidancly.guidancly_api.stop.Dto.StopDTO;
import com.guidancly.guidancly_api.stop.dao.entities.Stop;

public interface StopMapper {
    StopDTO fromStopToStopDTO(Stop stop);
    Stop fromDTOtoStop(StopDTO  stopDTO);
}
