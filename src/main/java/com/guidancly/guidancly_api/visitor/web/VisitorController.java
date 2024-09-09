package com.guidancly.guidancly_api.visitor.web;

import com.guidancly.guidancly_api.visitor.dao.entities.Visitor;
import com.guidancly.guidancly_api.visitor.dto.VisitorDTO;
import com.guidancly.guidancly_api.visitor.services.VisitorManager;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/visitor")
public class VisitorController {

        private VisitorManager visitorManager;

    public VisitorController(VisitorManager visitorManager) {
        this.visitorManager = visitorManager;
    }

    @GetMapping("/visitors")
        public Collection<VisitorDTO> getAll(){
            return visitorManager.getAll();
        }



    @PostMapping("/book")
    public VisitorDTO book(@RequestParam("id") Long idTour,@RequestHeader("Authorization") String token){
        System.out.println(idTour);
        return visitorManager.book(idTour,token);
    }
    @PostMapping("/checkVisitorTour")
    public VisitorDTO checkVisitorTour(@RequestParam("id") Long idTour,@RequestHeader("Authorization") String token){
        System.out.println(idTour);
        return visitorManager.checkVisitorTour(idTour,token);
    }





}
