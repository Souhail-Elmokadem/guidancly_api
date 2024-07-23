package com.guidancly.guidancly_api.visitor.services;

import com.guidancly.guidancly_api.visitor.dao.repositories.VisitorRepository;
import com.guidancly.guidancly_api.visitor.dto.VisitorDTO;
import com.guidancly.guidancly_api.visitor.mappers.VisitorMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class VisitorService implements VisitorManager{



    private VisitorMapper visitorMapper;
    private VisitorRepository visitorRepository;


    public VisitorService(VisitorMapper visitorMapper, VisitorRepository visitorRepository) {
        this.visitorMapper = visitorMapper;
        this.visitorRepository = visitorRepository;
    }


    @Override
    public Collection<VisitorDTO> getAll(){
        return visitorRepository.findAll().stream().map(visitorMapper::ConvertToDTO).collect(Collectors.toList());
    }

}
