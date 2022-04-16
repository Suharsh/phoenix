package edu.sjsu.cmpe275.lab2.phoenix.service;

import org.springframework.http.ResponseEntity;

public interface OpponentService {
    public ResponseEntity<String> addOpponent(String id1, String id2);

    public ResponseEntity<String> removeOpponent(String id1, String id2);
}
