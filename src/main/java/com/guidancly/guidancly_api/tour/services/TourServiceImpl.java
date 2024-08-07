package com.guidancly.guidancly_api.tour.services;

import com.guidancly.guidancly_api.common.services.BaseImpl;
import com.guidancly.guidancly_api.guide.dao.entities.Guide;
import com.guidancly.guidancly_api.guide.dao.repositories.GuideRepository;
import com.guidancly.guidancly_api.location.dao.entities.Location;
import com.guidancly.guidancly_api.location.dao.repositories.LocationRepository;
import com.guidancly.guidancly_api.stop.dao.entities.Stop;
import com.guidancly.guidancly_api.stop.dao.repositories.StopRepository;
import com.guidancly.guidancly_api.stop.mappers.StopMapper;
import com.guidancly.guidancly_api.tour.dao.entities.Tour;
import com.guidancly.guidancly_api.tour.dao.repositories.TourRepository;
import com.guidancly.guidancly_api.tour.dto.TourDTO;
import com.guidancly.guidancly_api.tour.dto.TourDtoReceive;
import com.guidancly.guidancly_api.tour.mappers.TourMapper;
import com.guidancly.guidancly_api.stop.Dto.StopDTO;
import com.guidancly.guidancly_api.user.dao.entities.User;
import com.guidancly.guidancly_api.user.dao.repositories.UserRepository;
import com.guidancly.guidancly_api.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class TourServiceImpl implements TourService{

    private final TourRepository tourRepository;
    private final TourMapper tourMapper;
    private final StopMapper stopMapper;
    @Autowired
    private BaseImpl baseImpl;

    @Autowired
    private StopRepository stopRepository;

    @Autowired
    private GuideRepository guideRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Collection<TourDTO> getAllTours() {
        return tourRepository.findAll()
                .stream().map(tour -> tourMapper.convertToDTO(tour))
                .collect(Collectors.toList());
    }

    @Override
    public TourDTO getTour(Long TourId) {

        return tourMapper.convertToDTO(
                tourRepository.findById(TourId).get()
        );
    }

    @Override
    public TourDTO getTourByGuide(Long GuideId) {

        return tourMapper.convertToDTO(
                tourRepository.findAllByGuideId(GuideId).get()
        );
    }

    @Override
    public TourDTO getTourByDepartLocations(Location Depart) {
        return null;
    }

    @Override

    public TourDTO createTour(TourDtoReceive tour,String token) {
        UserDto userDto = baseImpl.getUserByToken(token);
        Guide guide = guideRepository.findByEmail(userDto.getEmail());
        List<Stop> stopslist =  new ArrayList<>();
        System.out.println(stopRepository.save(new Stop(null,"",null,null,"")));
        tour.getStops().stream().map(s->{
            locationRepository.save(s.getLocation());
            stopslist.add(new Stop(null,s.getName(),s.getLocation(),null,""));
            return s;
        }).collect(Collectors.toList());

       TourDTO tourDTO = new TourDTO();
       tourDTO.setTitle(tour.getTitle());
       tourDTO.setStops(tour.getStops());
       tourDTO.setDepart(tour.getDepart());


       Tour tour2 = new Tour();
       BeanUtils.copyProperties(tourDTO,tour2);
       tour2.setStops(stopslist);

       locationRepository.save(tour.getDepart().getLocation());

       Stop s = stopRepository.save(stopMapper.fromDTOtoStop(tour.getDepart()));


       stopRepository.saveAll(stopslist);
       tour2.setDepart(s);
       tour2.setGuide(guide);

        System.out.println(tour2);
        Tour savedTour = tourRepository.save(tour2);
        System.out.println(savedTour);
        return tourMapper.convertToDTO(savedTour);
    }

    @Override
    public TourDTO updateTour(TourDTO tourDTO) {
        Tour tourToSave = tourMapper.covertToTour(tourDTO);
        TourDTO savedTour = tourMapper.convertToDTO(
                tourRepository.save(tourToSave)
        );
        return savedTour;
    }

    @Override
    public void DeleteTour(Long TourId) {
            tourRepository.deleteById(TourId);
    }
}
