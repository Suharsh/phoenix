package edu.sjsu.cmpe275.lab2.phoenix.model;

import javax.persistence.*;

@Entity
@Table(name="Opponent")
@IdClass(OpponentKey.class)
public class Opponent {
    @Id
    @Column(name="player_id")
    private String playerId;
    @Id
    @Column(name="opponent_id")
    private String opponentId;

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getOpponentId() {
        return opponentId;
    }

    public void setOpponentId(String opponentId) {
        this.opponentId = opponentId;
    }
}
