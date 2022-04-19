package edu.sjsu.cmpe275.lab2.phoenix.controller;

import edu.sjsu.cmpe275.lab2.phoenix.model.Team;
import edu.sjsu.cmpe275.lab2.phoenix.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/phoenix")
public class HealthCheckController {
    @Autowired
    private TeamRepository teamRepository;

    /***
     * Used for simple health check purpose of the service; should return "success" when the endpoint is hit
     *
     * @return "success"
     */
    @GetMapping("/ping")
    public String ping(){
        return "success";
    }

    /***
     * Should return database connection, by fetching data from one of the DB tables;
     *
     * @return Team data
     */
    @GetMapping("/checkDB")
    public Iterable<Team> checkDB() {
        Iterable<Team> tt = teamRepository.findAll();
        System.out.println(tt);
        return tt;
    }
}
