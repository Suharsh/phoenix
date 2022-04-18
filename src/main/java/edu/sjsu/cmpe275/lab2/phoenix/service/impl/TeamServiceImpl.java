package edu.sjsu.cmpe275.lab2.phoenix.service.impl;

import edu.sjsu.cmpe275.lab2.phoenix.exception.TeamNotFoundException;
import edu.sjsu.cmpe275.lab2.phoenix.model.Address;
import edu.sjsu.cmpe275.lab2.phoenix.model.Player;
import edu.sjsu.cmpe275.lab2.phoenix.model.Team;
import edu.sjsu.cmpe275.lab2.phoenix.repository.PlayerRepository;
import edu.sjsu.cmpe275.lab2.phoenix.repository.TeamRepository;
import edu.sjsu.cmpe275.lab2.phoenix.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Team getTeam(String id) {
        Optional<Team> team = teamRepository.findById(id);
        return team.orElseThrow(()-> new TeamNotFoundException());
    }


    @Override
    public Team createTeam(String name, String description, String street, String city, String state, String zip) {
        Team team = new Team();
        Address address = new Address();
        address.setCity(city);
        address.setState(state);
        address.setZip(zip);
        address.setStreet(street);
        team.setName(name);
        team.setDescription(description);
        team.setAddress(address);
        Team temp =  teamRepository.saveAndFlush(team);
        return temp;
    }

//    @Override
//    public Team updateTeam(String id, String name, String description, String street, String city, String state, String zip) {
//        List<Team> teams = teamRepository.findAll();
//        for(Team team:teams){
//            String Tid = team.getId().toString();
//            if(Tid.equals(id)){
//
//                Address address = team.getAddress();
//                if(address!=null){
//                    address.setCity(city);
//                    address.setState(state);
//                    address.setZip(zip);
//                    address.setStreet(street);
//
//                }
//
//                team.setName(name);
//                team.setDescription(description);
//                team.setAddress(address);
//                Team temp =  teamRepository.saveAndFlush(team);
//                return temp;
//
//            }
//        }
//        return null;
//
//    }

    @Override
    public Team updateTeam(String id, String name, String description, String street, String city, String state, String zip) {
        List<Team> teams = teamRepository.findAll();

        Team team = teamRepository.findById(id).orElse(null);
        if(team!=null){
            Address address = team.getAddress();
                if(address!=null){
                    address.setCity(city);
                    address.setState(state);
                    address.setZip(zip);
                    address.setStreet(street);

                }

                team.setName(name);
                team.setDescription(description);
                team.setAddress(address);
                Team temp =  teamRepository.saveAndFlush(team);
                return temp;
        }else{
            return null;
        }
//        return null;

    }

    public Team deleteTeam(String id){
        Team team = teamRepository.findById(id).orElse(null);
        team.getPlayers().forEach(player -> player.setTeam(null));
        playerRepository.saveAll(team.getPlayers());
        teamRepository.deleteById(id);
        return team;
    }
}
