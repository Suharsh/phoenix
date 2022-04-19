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


/**
 * Service Implementation for players
 */
@Service
@Transactional
public class OpponentServiceImpl implements OpponentService {
    @Autowired
    PlayerRepository playerRepository;

    /**
     * Adds two players as opponents of each other, given their ID's
     * throws 404 Not Found Exception if either of the player is not present in the database
     *
     * @param id1 id of the player1
     * @param id2 if of the player2
     * @return string stating that player1 is not opponent of player2
     */
    public ResponseEntity<String> addOpponent(String id1, String id2){

        Player player1 = playerRepository.findById(id1).orElse(null);
        Player player2 = playerRepository.findById(id2).orElse(null);

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

    /**
     * Removes two players as opponents of each other, given their ID's
     * throws 404 Not Found Exception if either of the player is not present in the database
     *
     * @param id1 id of the player1
     * @param id2 if of the player2
     * @return string stating that player1 is not opponent of player2
     */
    public ResponseEntity<String> removeOpponent(String id1, String id2){

        Player player1 = playerRepository.findById(id1).orElse(null);
        Player player2 = playerRepository.findById(id2).orElse(null);

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
