package com.guidancly.guidancly_api.tour.web;


import com.guidancly.guidancly_api.location.dao.entities.Location;
import com.guidancly.guidancly_api.tour.dto.TourDTO;
import com.guidancly.guidancly_api.tour.dto.TourDtoReceive;
import com.guidancly.guidancly_api.tour.services.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tours")
@RequiredArgsConstructor
public class TourController {

    private final TourService tourService;


    @GetMapping("/getAllTours")
    ResponseEntity<?> getAllTours(){
        return new ResponseEntity<>(tourService.getAllTours() , HttpStatus.OK);
    }


    @GetMapping("/getToursByGuide/{id}")
    ResponseEntity<?> getToursByGuide(@PathVariable Long guideId){
        return new ResponseEntity<>(tourService.getTourByGuide(guideId) , HttpStatus.OK);
    }


    @GetMapping("/getTour/{id}")
    ResponseEntity<?> getTourById(@PathVariable Long tourId){
        return new ResponseEntity<>(tourService.getTour(tourId) , HttpStatus.OK);
    }


    @GetMapping("/getToursByDepartLocation")
    ResponseEntity<?> getToursByDepartLocation(@RequestBody Location depart){
        return new ResponseEntity<>(tourService.getTourByDepartLocations(depart) , HttpStatus.OK);
    }


    @PostMapping("/create")
    ResponseEntity<?> createTour(@RequestBody TourDtoReceive tour){
        System.out.println(tour.toString());
        return new ResponseEntity<>(tourService.createTour(tour), HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deleteTour(@PathVariable Long tourId){
        tourService.DeleteTour(tourId);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
