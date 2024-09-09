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
import com.guidancly.guidancly_api.tour.dto.TourBookingDTO;
import com.guidancly.guidancly_api.tour.dto.TourDTO;
import com.guidancly.guidancly_api.tour.dto.TourDtoReceive;
import com.guidancly.guidancly_api.tour.mappers.TourMapper;
import com.guidancly.guidancly_api.user.dao.repositories.UserRepository;
import com.guidancly.guidancly_api.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class TourServiceImpl  implements TourService {

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
    public Page<TourDTO> getAllToursPage(String kw, int page, int size) {
        Page<TourDTO> tourDTOS = tourRepository.findByTitleContaining(kw,PageRequest.of(page,size))
                .map(tourMapper::convertToDTO);
        return tourDTOS;
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
    public List<TourBookingDTO> getTourByCurrentLocations(Double lat, Double lng) {

        System.out.println(lat+" +"+lng);
        List<Tour> tours = tourRepository.findToursNearby(lat,lng,1000);

        ///tours.stream().forEach((t)->System.out.println(t.getTitle()));


        List<TourBookingDTO> tourDTO= tours.stream().map(tourMapper::covertToBookingDTO).collect(Collectors.toList());

        System.out.println("+++++++++++++++++++++++++++");
        System.out.println(tourDTO);

        return tourDTO;
    }


    @Override

    public TourDTO createTour(TourDtoReceive tour,String token) throws IOException{
        UserDto userDto = baseImpl.getUserByToken(token);
        Guide guide = guideRepository.findByEmail(userDto.getEmail());
        if (guide==null){
            throw new RuntimeException("User not a have guide Role");
        }
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
       tourDTO.setDescription(tour.getDescription());


       Tour tour2 = new Tour();
       BeanUtils.copyProperties(tourDTO,tour2);
       tour2.setPrice(tour.getPrice());

       List<String> tourImages =  tour.getImages().stream().map(t->{
           try {
               return baseImpl.uploadBase64Image(t);
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
       }).collect(Collectors.toList());
       tour2.setImages(tourImages);
       tour2.setStops(stopslist);
       tour2.setDate(new Date());
       tour2.setEstimatedTime(tour.getEstimatedTime());
       tour2.setDistance(tour.getDistance());

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
