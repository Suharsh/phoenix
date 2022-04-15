package edu.sjsu.cmpe275.lab2.phoenix.controller;

import edu.sjsu.cmpe275.lab2.phoenix.dto.PlayerRequestDTO;
import edu.sjsu.cmpe275.lab2.phoenix.model.Player;
import edu.sjsu.cmpe275.lab2.phoenix.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

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
                               @RequestParam(required = false) String description,
                               @RequestParam(required = false) String street,
                               @RequestParam(required = false) String city,
                               @RequestParam(required = false) String state,
                               @RequestParam(required = false) String zip){
        PlayerRequestDTO player = new PlayerRequestDTO(firstName,lastName,email,description,teamId,street,city,state,zip);
        return playerService.createPlayer(player);
    }

    @PutMapping("/player/{id}")
    public Player updatePlayer(
            @PathVariable String id,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam(required = false) String teamId,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String street,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String zip){
        PlayerRequestDTO player = new PlayerRequestDTO(firstName,lastName,email,description,teamId,street,city,state,zip);
        player.setId(id);
        return playerService.updatePlayer(player);
    }

    @DeleteMapping("/player/{id}")
    public void deletePlayer(
            @PathVariable String id){
        playerService.deletePlayer(id);
    }
}
