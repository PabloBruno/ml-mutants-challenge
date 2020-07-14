package com.mlmutants.status.controller;


import com.mlmutants.status.payload.StatusDetectionPayload;
import com.mlmutants.status.service.StatusDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatusDetectorController {

    @Autowired
    StatusDetectionService statusDetectionService;

    /**
     * Devuelve el status de la deteccion de adn
     * @return un objeto StatusDetectionPayload con la informacion del count de mutantes y humanos y el ratio.
     */
    @GetMapping()
    public ResponseEntity<StatusDetectionPayload> getStatusDetector(){
           StatusDetectionPayload statusDetectionPayload = statusDetectionService.getStatusDetection();
       if (statusDetectionPayload != null) {
           return ResponseEntity.ok()
                                .body(statusDetectionPayload);
       }


       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }
}
