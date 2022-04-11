package edu.sjsu.cmpe275.lab2.phoenix.service.impl;

import edu.sjsu.cmpe275.lab2.phoenix.exception.PlayerNotFoundException;
import edu.sjsu.cmpe275.lab2.phoenix.model.Player;
import edu.sjsu.cmpe275.lab2.phoenix.repository.PlayerRepository;
import edu.sjsu.cmpe275.lab2.phoenix.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public Player getPlayer(String id){
        Optional<Player> player =  playerRepository.findById(id);
        System.out.println(player.get().getTeam().getName());
        return player.orElseThrow(()-> new PlayerNotFoundException());
    }
}
