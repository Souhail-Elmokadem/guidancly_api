package com.guidancly.guidancly_api.guide.web;


import com.guidancly.guidancly_api.guide.services.GuideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/guides")
@RequiredArgsConstructor
public class GuideController {

    private final GuideService guideService;

    @GetMapping("/getAllGuides")
    ResponseEntity<?> getAllGuides(){
        return new ResponseEntity<>(guideService.getAllGuides() , HttpStatus.OK);
    }


    @GetMapping("/getToursByGuide")
    ResponseEntity<?> getToursByGuide(@RequestHeader("Authorization") String token){
        return new ResponseEntity<>(guideService.getToursByGuide(token) , HttpStatus.OK);
    }

}
