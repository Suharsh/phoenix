package edu.sjsu.cmpe275.lab2.phoenix.controller;

import edu.sjsu.cmpe275.lab2.phoenix.model.Player;
import edu.sjsu.cmpe275.lab2.phoenix.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/phoenix")
public class PlayerController {

    @Autowired
    public PlayerService playerService;

    @GetMapping("/player/{id}")
    public Player getPlayer(@PathVariable String id){
        return playerService.getPlayer(id);
    }

    @PostMapping("/player")
    public Player createPlayer(@RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam String email,
                               @RequestParam(required = false) String teamId,
                               @RequestParam(required = false) String description){
        return playerService.createPlayer(firstName,lastName,email, description, teamId);
    }

    @PutMapping("/player/{id}")
    public Player updatePlayer(
            @PathVariable String id,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam(required = false) String teamId,
            @RequestParam(required = false) String description){
        return playerService.updatePlayer(id,firstName,lastName,email,description, teamId);
    }

    @DeleteMapping("/player/{id}")
    public void deletePlayer(
            @PathVariable String id){
        playerService.deletePlayer(id);
    }
}
