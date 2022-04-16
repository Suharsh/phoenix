package edu.sjsu.cmpe275.lab2.phoenix.controller;

import edu.sjsu.cmpe275.lab2.phoenix.model.Team;
import edu.sjsu.cmpe275.lab2.phoenix.service.TeamService;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    public TeamService teamService;

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable String id){

        Team team =  teamService.getTeam(id);
        if(team!= null){
            return new ResponseEntity<Team>(team,HttpStatus.OK);
        }
        return new ResponseEntity<Team>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("")
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

    @PutMapping("/{id}")
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Team> deleteTeam(@PathVariable String id){
        Team team = teamService.deleteTeam(id);
        if(team != null){
            return new ResponseEntity<Team>(team,HttpStatus.OK);
        }
        return new ResponseEntity<Team>(HttpStatus.NOT_FOUND);
    }
}
