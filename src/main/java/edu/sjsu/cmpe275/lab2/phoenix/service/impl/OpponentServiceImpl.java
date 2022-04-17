package edu.sjsu.cmpe275.lab2.phoenix.service.impl;

import edu.sjsu.cmpe275.lab2.phoenix.model.Player;
import edu.sjsu.cmpe275.lab2.phoenix.repository.PlayerRepository;
import edu.sjsu.cmpe275.lab2.phoenix.service.OpponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OpponentServiceImpl implements OpponentService {
    @Autowired
    PlayerRepository playerRepository;

    public ResponseEntity<String> addOpponent(String id1, String id2){

        Player player1 = new Player();
        Player player2 = new Player();
        List<Player> players = playerRepository.findAll();
        for(Player player:players){
            String id = player.getId();
            if(id.equals(id1)){
                player1 = player;
            }
            if(id.equals(id2)){
                player2 = player;
            }
        }
        if(player1== null || player2 == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Player> opponentsP1 = player1.getOpponents();
        List<Player> opponentsP2 = player2.getOpponents();
        if(opponentsP1.contains(player2))return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(opponentsP2.contains(player1))return new ResponseEntity<>(HttpStatus.BAD_REQUEST);



        opponentsP1.add(player2);
        player1.setOpponents(opponentsP1);
        playerRepository.save(player1);
        opponentsP2.add(player1);
        player2.setOpponents(opponentsP2);
        playerRepository.save(player2);

        return new ResponseEntity<String>(player1.getFirstName()+" is opponent of "+player2.getFirstName(), HttpStatus.OK);

    }

    public ResponseEntity<String> removeOpponent(String id1, String id2){

        Player player1 = new Player();
        Player player2 = new Player();
        List<Player> players = playerRepository.findAll();
        for(Player player:players){
            String id = player.getId();
            if(id.equals(id1)){
                player1 = player;
            }
            if(id.equals(id2)){
                player2 = player;
            }
        }
        if(player1== null || player2 == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Player> opponentsP1 = player1.getOpponents();
        List<Player> opponentsP2 = player2.getOpponents();
        if(!opponentsP1.contains(player2))return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(!opponentsP2.contains(player1))return new ResponseEntity<>(HttpStatus.BAD_REQUEST);



        opponentsP1.remove(player2);
        player1.setOpponents(opponentsP1);
        playerRepository.save(player1);
        opponentsP2.remove(player1);
        player2.setOpponents(opponentsP2);
        playerRepository.save(player2);

        return new ResponseEntity<String>(player1.getFirstName()+" is not opponent of "+player2.getFirstName(), HttpStatus.OK);

    }


}
