package edu.sjsu.cmpe275.lab2.phoenix.service;

import edu.sjsu.cmpe275.lab2.phoenix.model.Team;

public interface TeamService {
    public Team getTeam(String id);
    public Team createTeam(String name, String description, String street, String city, String state, String zip);
    public Team updateTeam(String id, String name, String description, String street, String city, String state, String zip);
}
