package com.guidancly.guidancly_api.visitor.services;

import com.guidancly.guidancly_api.common.services.BaseImpl;
import com.guidancly.guidancly_api.guide.dao.entities.Guide;
import com.guidancly.guidancly_api.tour.dao.entities.Tour;
import com.guidancly.guidancly_api.tour.dao.repositories.TourRepository;
import com.guidancly.guidancly_api.user.dto.UserDto;
import com.guidancly.guidancly_api.visitor.dao.entities.Visitor;
import com.guidancly.guidancly_api.visitor.dao.repositories.VisitorRepository;
import com.guidancly.guidancly_api.visitor.dto.VisitorDTO;
import com.guidancly.guidancly_api.visitor.mappers.VisitorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitorService implements VisitorManager{

    @Autowired
    private BaseImpl baseImpl;

    private VisitorMapper visitorMapper;
    private VisitorRepository visitorRepository;

    private TourRepository tourRepository;

    public VisitorService(VisitorMapper visitorMapper, VisitorRepository visitorRepository, TourRepository tourRepository) {
        this.visitorMapper = visitorMapper;
        this.visitorRepository = visitorRepository;
        this.tourRepository = tourRepository;
    }



    @Override
    public Collection<VisitorDTO> getAll(){
        return visitorRepository.findAll().stream().map(visitorMapper::ConvertToDTO).collect(Collectors.toList());
    }

    @Override
    public VisitorDTO book(Long idTour, String token) {
        UserDto userDto = baseImpl.getUserByToken(token);
        Visitor visitor = visitorRepository.findByEmailOrNumber(userDto.getEmail(),userDto.getNumber());
        if (visitor==null){
            throw new RuntimeException("User not a have visitor Role");
        }

        Tour tour = tourRepository.findById(idTour).orElseThrow(()->new RuntimeException("Tour Not Found"));


        tour.setNumberOfVisitors(tour.getNumberOfVisitors()+1);


        tourRepository.save(tour);

        visitor.setCurrentTour(tour);


        return visitorMapper.ConvertToDTO(visitorRepository.save(visitor));
    }

    @Override
    public VisitorDTO checkVisitorTour(Long idTour, String token) {
        UserDto userDto = baseImpl.getUserByToken(token);
        Visitor visitor = visitorRepository.findByEmailOrNumber(userDto.getEmail(),userDto.getNumber());
        if (visitor==null){
            throw new RuntimeException("User not a have visitor Role");
        }

//        Tour tour = tourRepository.findById(idTour).orElseThrow(()->new RuntimeException("Tour Not Found"));

        return visitorMapper.ConvertToDTO(visitor);

    }

}
