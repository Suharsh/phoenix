package edu.sjsu.cmpe275.lab2.phoenix.controller;

import edu.sjsu.cmpe275.lab2.phoenix.dto.PlayerRequestDTO;
import edu.sjsu.cmpe275.lab2.phoenix.model.Player;
import edu.sjsu.cmpe275.lab2.phoenix.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/phoenix")
public class PlayerController {

    @Autowired
    public PlayerService playerService;

    /**
     * Fetch the details of the player, given a player id
     *
     * @param id the ID of the player
     * @return the player with the given ID
     */
    @GetMapping("/player/{id}")
    public Player getPlayer(@PathVariable String id){
        return playerService.getPlayer(id);
    }

    /***
     * create a player with the given details. First name, last name and email are mandatory parameters
     * for a player
     *
     * @param firstName     the first name of the player
     * @param lastName      the last name of the player
     * @param email         the email id of the player
     * @param teamId        the team ID of the player
     * @param description   description of the player
     * @param street        player address information - street
     * @param city          player address information - city
     * @param state         player address information - state
     * @param zip           player address information - zip code
     * @return              the deep form of the player entity
     */
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

    /***
     * Update the given player with an ID. First name, last name and email are the mandatory parameters
     *
     * @param firstName     the first name of the player
     * @param lastName      the last name of the player
     * @param email         the email id of the player
     * @param teamId        the team ID of the player
     * @param description   description of the player
     * @param street        player address information - street
     * @param city          player address information - city
     * @param state         player address information - state
     * @param zip           player address information - zip code
     * @return
     */
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

    /***
     * Delete a player with the given ID
     *
     * @param id        the ID of the player to be deleted
     * @return          the deep form the player object that is being deleted
     */
    @DeleteMapping("/player/{id}")
    public Player deletePlayer(
            @PathVariable String id){
        return playerService.deletePlayer(id);
    }
}
