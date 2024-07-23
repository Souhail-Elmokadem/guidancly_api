package com.guidancly.guidancly_api.tour.services;

import com.guidancly.guidancly_api.location.dao.entities.Location;
import com.guidancly.guidancly_api.tour.dao.repositories.TourRepository;
import com.guidancly.guidancly_api.tour.dto.TourDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@RequiredArgsConstructor
@Service
public class TourServiceImpl implements TourService{

    private final TourRepository tourRepository;


    @Override
    public Collection<TourDTO> getAllTours() {
        return List.of();
    }

    @Override
    public TourDTO getTour(Long TourId) {
        return null;
    }

    @Override
    public TourDTO getTourByGuide(Long GuideId) {
        return null;
    }

    @Override
    public TourDTO getTourByDepartLocations(Location Depart) {
        return null;
    }

    @Override
    public TourDTO createTour(TourDTO tour) {
        return null;
    }

    @Override
    public TourDTO updateTour(Long TourId, TourDTO tourDTO) {
        return null;
    }

    @Override
    public void DeleteTour(Long TourId) {

    }
}
