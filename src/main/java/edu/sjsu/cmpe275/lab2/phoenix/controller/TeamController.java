package edu.sjsu.cmpe275.lab2.phoenix.controller;

import edu.sjsu.cmpe275.lab2.phoenix.model.Team;
import edu.sjsu.cmpe275.lab2.phoenix.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phoenix")
public class TeamController {

    @Autowired
    public TeamService teamService;

    @GetMapping("/team/{id}")
    public Team getTeam(@PathVariable String id){
        return teamService.getTeam(id);
    }
}
