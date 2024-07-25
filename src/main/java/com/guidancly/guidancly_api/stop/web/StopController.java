package com.guidancly.guidancly_api.stop.web;


import com.guidancly.guidancly_api.stop.services.StopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stops")
@RequiredArgsConstructor
public class StopController {

    private final StopService stopService;

    @GetMapping("/getAllStops")
    ResponseEntity<?> getAllStops(){
        return new ResponseEntity<>(stopService.getAllStops(), HttpStatus.OK);
    }




}
