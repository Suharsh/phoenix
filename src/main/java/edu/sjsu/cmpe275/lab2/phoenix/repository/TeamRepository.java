package edu.sjsu.cmpe275.lab2.phoenix.repository;

import edu.sjsu.cmpe275.lab2.phoenix.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team,String> {

}
