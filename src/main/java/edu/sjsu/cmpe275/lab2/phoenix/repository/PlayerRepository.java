package edu.sjsu.cmpe275.lab2.phoenix.repository;

import edu.sjsu.cmpe275.lab2.phoenix.model.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player,String> {
    public boolean existsByEmail(String email);
}
