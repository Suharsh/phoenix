package edu.sjsu.cmpe275.lab2.phoenix.service;

import edu.sjsu.cmpe275.lab2.phoenix.dto.PlayerRequestDTO;
import edu.sjsu.cmpe275.lab2.phoenix.model.Player;

public interface PlayerService {
    public Player getPlayer(String id);
    public Player createPlayer(PlayerRequestDTO player);
    public Player updatePlayer(PlayerRequestDTO player);
    public Player deletePlayer(String id);
}
