package edu.sjsu.cmpe275.lab2.phoenix.controller;

import edu.sjsu.cmpe275.lab2.phoenix.model.Team;
import edu.sjsu.cmpe275.lab2.phoenix.service.OpponentService;
import edu.sjsu.cmpe275.lab2.phoenix.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/opponents")
public class OpponentController {

    @Autowired
    public OpponentService opponentService;

    @PutMapping("/{id1}/{id2}")
    public ResponseEntity<String> addOpponent(@PathVariable String id1,
                                           @PathVariable String id2){
        return opponentService.addOpponent(id1,id2);

    }

    @DeleteMapping("/{id1}/{id2}")
    public ResponseEntity<String> removeOpponent(@PathVariable String id1,
                                              @PathVariable String id2){
        return opponentService.removeOpponent(id1,id2);

    }

}
