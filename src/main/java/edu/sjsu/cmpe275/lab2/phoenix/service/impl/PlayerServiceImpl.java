package edu.sjsu.cmpe275.lab2.phoenix.service.impl;

import edu.sjsu.cmpe275.lab2.phoenix.exception.InvalidTeamException;
import edu.sjsu.cmpe275.lab2.phoenix.exception.PlayerAlreadyExist;
import edu.sjsu.cmpe275.lab2.phoenix.exception.PlayerNotFoundException;
import edu.sjsu.cmpe275.lab2.phoenix.model.Player;
import edu.sjsu.cmpe275.lab2.phoenix.model.Team;
import edu.sjsu.cmpe275.lab2.phoenix.repository.PlayerRepository;
import edu.sjsu.cmpe275.lab2.phoenix.repository.TeamRepository;
import edu.sjsu.cmpe275.lab2.phoenix.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Player getPlayer(String id){
        Optional<Player> player =  playerRepository.findById(id);
        System.out.println(player.get().getTeam().getName());
        return player.orElseThrow(()-> new PlayerNotFoundException());
    }

    @Override
    public Player createPlayer(String firstName, String lastName, String email, String description,String teamId) {
        Optional<Team> team = Optional.empty();
        if (teamId != null) {
            team = teamRepository.findById(teamId);
            if (!team.isPresent())
                throw new InvalidTeamException();
        }
        if (playerRepository.existsByEmail(email)) {
            throw new PlayerAlreadyExist();
        }
        Player player = new Player(firstName, lastName,email,description,team.orElse(null));
        return playerRepository.save(player);
    }

    @Override
    public Player updatePlayer(String id, String firstName, String lastName, String email, String description,String teamId) {
        Optional<Team> team = Optional.empty();
        if (teamId != null) {
            team = teamRepository.findById(teamId);
            if (!team.isPresent())
                throw new InvalidTeamException();
        }
        if(!playerRepository.existsById(id))
            throw new PlayerNotFoundException();
        Player player = new Player(firstName, lastName,email,description,team.orElse(null));
        player.setId(id);
        return playerRepository.save(player);
    }

    @Override
    public void deletePlayer(String id) {
        if(!playerRepository.existsById(id))
            throw new PlayerNotFoundException();
        playerRepository.deleteById(id);
    }
}
