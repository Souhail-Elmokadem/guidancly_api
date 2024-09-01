package com.guidancly.guidancly_api.tour.web;


import com.guidancly.guidancly_api.location.dao.entities.Location;
import com.guidancly.guidancly_api.tour.dto.ResponseApi;
import com.guidancly.guidancly_api.tour.dto.TourDTO;
import com.guidancly.guidancly_api.tour.dto.TourDtoReceive;
import com.guidancly.guidancly_api.tour.services.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/tours")
@RequiredArgsConstructor
public class TourController {

    private final TourService tourService;


    @GetMapping("/getAllTours")
    ResponseEntity<?> getAllTours(){
        return new ResponseEntity<>(tourService.getAllTours() , HttpStatus.OK);
    }
    @GetMapping("/getAllToursPage")
    ResponseApi<?> getAllToursPage(
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "6") int size,
            @RequestParam(name = "search",defaultValue = "") String kw
    ){
        Page<TourDTO> tourDTOS = tourService.getAllToursPage(kw,page,size);
        return new ResponseApi<TourDTO>( tourDTOS.getContent(), (int)tourDTOS.getTotalElements());
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
    @GetMapping("/getToursByCurrentLocation")
    ResponseEntity<?> getToursByCurrentLocation(@RequestParam double lat, @RequestParam double lng){
        return new ResponseEntity<>(tourService.getTourByCurrentLocations(lat,lng) , HttpStatus.OK);
    }


    @PostMapping("/create")
    ResponseEntity<?> createTour(@RequestBody TourDtoReceive tour,@RequestHeader("Authorization") String token) throws IOException {
        System.out.println(tour.toString());
        return new ResponseEntity<>(tourService.createTour(tour,token), HttpStatus.CREATED);
    }



    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deleteTour(@PathVariable Long tourId){
        tourService.DeleteTour(tourId);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
