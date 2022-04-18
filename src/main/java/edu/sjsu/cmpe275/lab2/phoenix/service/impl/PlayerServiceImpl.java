package edu.sjsu.cmpe275.lab2.phoenix.service.impl;

import edu.sjsu.cmpe275.lab2.phoenix.dto.PlayerRequestDTO;
import edu.sjsu.cmpe275.lab2.phoenix.exception.InvalidTeamException;
import edu.sjsu.cmpe275.lab2.phoenix.exception.PlayerAlreadyExist;
import edu.sjsu.cmpe275.lab2.phoenix.exception.PlayerNotFoundException;
import edu.sjsu.cmpe275.lab2.phoenix.model.Address;
import edu.sjsu.cmpe275.lab2.phoenix.model.Player;
import edu.sjsu.cmpe275.lab2.phoenix.model.Team;
import edu.sjsu.cmpe275.lab2.phoenix.repository.PlayerRepository;
import edu.sjsu.cmpe275.lab2.phoenix.repository.TeamRepository;
import edu.sjsu.cmpe275.lab2.phoenix.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Player getPlayer(String id){
        Optional<Player> player =  playerRepository.findById(id);
        return player.orElseThrow(()-> new PlayerNotFoundException());
    }

    @Override
    public Player createPlayer(PlayerRequestDTO player) {
        Optional<Team> team = Optional.empty();
        if (player.teamId != null) {
            team = teamRepository.findById(player.teamId);
            if (!team.isPresent())
                throw new InvalidTeamException();
        }
        if (playerRepository.existsByEmail(player.email)) {
            throw new PlayerAlreadyExist();
        }
        Address address = new Address(player.street, player.city,player.state,player.zip);
        Player playerEntity = new Player(player.firstName, player.lastName,player.email,player.description,team.orElse(null),address);
        return playerRepository.save(playerEntity);
    }

    @Override
    public Player updatePlayer(PlayerRequestDTO player) {
        Optional<Team> team = Optional.empty();
        if (player.teamId != null) {
            team = teamRepository.findById(player.teamId);
            if (!team.isPresent())
                throw new InvalidTeamException();
        }
        Player playerEntity = playerRepository.getById(player.id);
        if(playerEntity==null)
            throw new PlayerNotFoundException();
        Address address = new Address(player.street, player.city,player.state,player.zip);
        playerEntity.setAddress(address);
        playerEntity.setFirstName(player.firstName);
        playerEntity.setLastName(player.lastName);
        playerEntity.setEmail(player.email);
        playerEntity.setDescription(player.description);
        playerEntity.setTeam(team.orElse(null));
        return playerRepository.save(playerEntity);
    }

    @Override
    public Player deletePlayer(String id) {
        Player player = playerRepository.findById(id).orElse(null);
        if(player==null)
            throw new PlayerNotFoundException();
        player.getOpponents().forEach(opponent -> opponent.getOpponents().remove(player));
        playerRepository.saveAllAndFlush(player.getOpponents());
        playerRepository.deleteById(id);
        return player;
    }
}
