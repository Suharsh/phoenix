package edu.sjsu.cmpe275.lab2.phoenix.controller;

import edu.sjsu.cmpe275.lab2.phoenix.model.Team;
import edu.sjsu.cmpe275.lab2.phoenix.service.TeamService;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/phoenix")
public class TeamController {

    @Autowired
    public TeamService teamService;

    /**
     * Fetch the details of the Team, given a Team id
     *
     * @param id the ID of the Team
     * @return the Team with the given ID
     */
    @GetMapping("team/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable String id){

        Team team =  teamService.getTeam(id);
        if(team!= null){
            return new ResponseEntity<Team>(team,HttpStatus.OK);
        }
        return new ResponseEntity<Team>(HttpStatus.NOT_FOUND);

    }

    /***
     * create a Team with the given details. Name is a mandatory parameters
     *
     * @param name          name of the Team
     * @param description   description of the Team
     * @param street        Team address information - street
     * @param city          Team address information - city
     * @param state         Team address information - state
     * @param zip           Team address information - zip code
     * @return              the deep form of the Team entity
     */
    @PostMapping("/team")
    public ResponseEntity<Team> createTeam(@RequestParam(name="name") String name,
                                           @RequestParam(name="description",required=false) String description,
                                           @RequestParam(name="street",required=false) String street,
                                           @RequestParam(name="city",required=false) String city,
                                           @RequestParam(name="state",required=false) String state,
                                           @RequestParam(name="zip",required=false) String zip){
        if(name!= null){
            Team team = teamService.createTeam(name,description,street,city,state,zip);
            return new ResponseEntity<Team>(team,HttpStatus.OK);
        }
        return new ResponseEntity<Team>(HttpStatus.BAD_REQUEST);
    }

    /***
     * updates a Team with the given details. Name is a mandatory parameters
     *
     * @param id            Id of the team
     * @param name          name of the Team
     * @param description   description of the Team
     * @param street        Team address information - street
     * @param city          Team address information - city
     * @param state         Team address information - state
     * @param zip           Team address information - zip code
     * @return              the deep form of the Team entity
     */
    @PutMapping("team/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable String id,
                           @RequestParam(name="name") String name,
                           @RequestParam(name="description",required=false) String description,
                           @RequestParam(name="street",required=false) String street,
                           @RequestParam(name="city",required=false) String city,
                           @RequestParam(name="state",required=false) String state,
                           @RequestParam(name="zip",required=false) String zip){
        if(name== null){
            new ResponseEntity<Team>(HttpStatus.BAD_REQUEST);
        }
        Team team = teamService.updateTeam(id,name,description,street,city,state,zip);
        if(team != null){
            return new ResponseEntity<Team>(team,HttpStatus.OK);
        }
        return new ResponseEntity<Team>(HttpStatus.NOT_FOUND);
    }

    /***
     * Delete a Team with the given ID
     *
     * @param id        the ID of the Team to be deleted
     * @return          the deep form the Team object that is being deleted
     */
    @DeleteMapping("team/{id}")
    public ResponseEntity<Team> deleteTeam(@PathVariable String id){
        Team team = teamService.deleteTeam(id);
        if(team != null){
            return new ResponseEntity<Team>(team,HttpStatus.OK);
        }
        return new ResponseEntity<Team>(HttpStatus.NOT_FOUND);
    }
}
