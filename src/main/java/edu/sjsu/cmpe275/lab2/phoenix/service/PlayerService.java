package edu.sjsu.cmpe275.lab2.phoenix.service;

import edu.sjsu.cmpe275.lab2.phoenix.model.Player;

public interface PlayerService {
    public Player getPlayer(String id);
    public Player createPlayer(String firstName, String lastName, String email, String description, String teamId);
    public Player updatePlayer(String id, String firstName, String lastName, String email, String description, String teamId);
    public void deletePlayer(String id);
}
