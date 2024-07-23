package com.guidancly.guidancly_api.visitor.mappers;

import com.guidancly.guidancly_api.visitor.dao.entities.Visitor;
import com.guidancly.guidancly_api.visitor.dto.VisitorDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class VisitorMapperImpl implements VisitorMapper{

    private ModelMapper modelMapper;

    public VisitorMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }



    @Override

    public VisitorDTO ConvertToDTO(Visitor visitor){
        return modelMapper.map(visitor,VisitorDTO.class);
    }
    @Override

    public Visitor ConvertToDTO(VisitorDTO visitorDTO){
        return modelMapper.map(visitorDTO,Visitor.class);
    }
}
