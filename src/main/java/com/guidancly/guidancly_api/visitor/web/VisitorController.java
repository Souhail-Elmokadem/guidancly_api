package com.guidancly.guidancly_api.visitor.web;

import com.guidancly.guidancly_api.visitor.dao.entities.Visitor;
import com.guidancly.guidancly_api.visitor.dto.VisitorDTO;
import com.guidancly.guidancly_api.visitor.services.VisitorManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class VisitorController {

        private VisitorManager visitorManager;

    public VisitorController(VisitorManager visitorManager) {
        this.visitorManager = visitorManager;
    }

    @GetMapping("/visitors")
        public Collection<VisitorDTO> getAll(){
            return visitorManager.getAll();
        }




}
