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
/***
 * Service level implementation for Team
 */
@Service
@Transactional
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerRepository playerRepository;


    /***
     * Gets the Team details for the given Id
     * throws team not found exception if the id is not found
     * @param id        Team Id
     * @return          Team object in its deep form
     */
    @Override
    public Team getTeam(String id) {
        Optional<Team> team = teamRepository.findById(id);
        return team.orElseThrow(()-> new TeamNotFoundException());
    }

    /***
     * Creates a team with the given details; Team ID is auto generated;
     *
     * @param name          name of the Team
     * @param description   description of the Team
     * @param street        Team address information - street
     * @param city          Team address information - city
     * @param state         Team address information - state
     * @param zip           Team address information - zip code
     * @return              the team object in its deep form
     */
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
        Team result =  teamRepository.saveAndFlush(team);
        return result;
    }

    /***
     * Updates a Team with the given details; Team ID is auto generated; players of a Team remains unchanged
     *
     * @param id            Team Id
     * @param name          name of the Team
     * @param description   description of the Team
     * @param street        Team address information - street
     * @param city          Team address information - city
     * @param state         Team address information - state
     * @param zip           Team address information - zip code
     * @return              the team object in its deep form
     */
    @Override
    public Team updateTeam(String id, String name, String description, String street, String city, String state, String zip) {

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


    }

    /***
     * Deletes the Team with the given ID;
     *
     * @param id        the ID of the Team to be deleted
     * @return          the Team object being deleted in its deep form
     */
    @Override
    public Team deleteTeam(String id){
        Team team = teamRepository.findById(id).orElse(null);
        if(team==null)
            throw new TeamNotFoundException();
        team.getPlayers().forEach(player -> player.setTeam(null));
        playerRepository.saveAll(team.getPlayers());
        teamRepository.deleteById(id);
        return team;
    }
}
