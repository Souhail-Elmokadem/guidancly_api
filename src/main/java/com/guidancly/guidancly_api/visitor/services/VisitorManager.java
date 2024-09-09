package com.guidancly.guidancly_api.visitor.services;

import com.guidancly.guidancly_api.visitor.dao.entities.Visitor;
import com.guidancly.guidancly_api.visitor.dto.VisitorDTO;

import java.util.Collection;

public interface VisitorManager {
    Collection<VisitorDTO> getAll();

    VisitorDTO book(Long idTour, String token);

    VisitorDTO checkVisitorTour(Long idTour, String token);
}
