package com.guidancly.guidancly_api.tour.services;

import com.guidancly.guidancly_api.location.dao.entities.Location;
import com.guidancly.guidancly_api.tour.dao.entities.Tour;
import com.guidancly.guidancly_api.tour.dao.repositories.TourRepository;
import com.guidancly.guidancly_api.tour.dto.TourDTO;
import com.guidancly.guidancly_api.tour.mappers.TourMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class TourServiceImpl implements TourService{

    private final TourRepository tourRepository;
    private final TourMapper tourMapper;


    @Override
    public Collection<TourDTO> getAllTours() {
        return tourRepository.findAll()
                .stream().map( tour -> tourMapper.convertToDTO(tour))
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
    public TourDTO createTour(TourDTO tour) {
        Tour tourToSave = tourMapper.covertToTour(tour);
        TourDTO savedTour = tourMapper.convertToDTO(
                tourRepository.save(tourToSave)
        );
        return savedTour;
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
