package com.guidancly.guidancly_api.visitor.mappers;

import com.guidancly.guidancly_api.visitor.dao.entities.Visitor;
import com.guidancly.guidancly_api.visitor.dto.VisitorDTO;

public interface VisitorMapper {

    VisitorDTO ConvertToDTO(Visitor visitor);

    Visitor ConvertToDTO(VisitorDTO visitorDTO);
}
