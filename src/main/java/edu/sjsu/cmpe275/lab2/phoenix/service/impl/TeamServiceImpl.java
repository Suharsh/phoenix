package edu.sjsu.cmpe275.lab2.phoenix.service.impl;

import edu.sjsu.cmpe275.lab2.phoenix.exception.TeamNotFoundException;
import edu.sjsu.cmpe275.lab2.phoenix.model.Team;
import edu.sjsu.cmpe275.lab2.phoenix.repository.TeamRepository;
import edu.sjsu.cmpe275.lab2.phoenix.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Team getTeam(String id) {
        Optional<Team> team = teamRepository.findById(id);
        return team.orElseThrow(()-> new TeamNotFoundException());
    }

    @Override
    public Team createTeam(String name, String description, String street, String city, String state, String zip) {
        return null;
    }

    @Override
    public Team updateTeam(String id, String name, String description, String street, String city, String state, String zip) {
        return null;
    }
}
