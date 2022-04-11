package edu.sjsu.cmpe275.lab2.phoenix.controller;

import edu.sjsu.cmpe275.lab2.phoenix.model.Player;
import edu.sjsu.cmpe275.lab2.phoenix.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phoenix")
public class PlayerController {

    @Autowired
    public PlayerService playerService;

    @GetMapping("/player/{id}")
    public Player getPlayer(@PathVariable String id){
        return playerService.getPlayer(id);
    }
}
