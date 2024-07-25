package com.guidancly.guidancly_api.stop.services;

import com.guidancly.guidancly_api.stop.Dto.StopDTO;
import com.guidancly.guidancly_api.stop.dao.entities.Stop;

import java.util.Collection;

public interface StopService {

    Collection<StopDTO> getAllStops();

}
