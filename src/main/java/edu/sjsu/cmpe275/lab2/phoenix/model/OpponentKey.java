package edu.sjsu.cmpe275.lab2.phoenix.model;

import java.io.Serializable;
import java.util.Objects;

public class OpponentKey implements Serializable {
    private String playerId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OpponentKey)) return false;
        OpponentKey that = (OpponentKey) o;
        return getPlayerId().equals(that.getPlayerId()) && getOpponentId().equals(that.getOpponentId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayerId(), getOpponentId());
    }
}
