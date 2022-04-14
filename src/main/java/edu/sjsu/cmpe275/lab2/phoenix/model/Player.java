package edu.sjsu.cmpe275.lab2.phoenix.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Player")
public class Player {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @Column(name="id")
    private String id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="email")
    private String email;
    @Column(name="description")
    private String description;

    @JsonIgnoreProperties("players")
    @ManyToOne(fetch=FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name="team_id", referencedColumnName = "id")
    private Team team;

    @ElementCollection
    @CollectionTable(name="Opponent",joinColumns = @JoinColumn(name = "player_id",referencedColumnName = "id"))
    @Column(name="opponent_id")
    private List<String> opponents;

    public Player(){

    }

    public Player(String firstName, String lastName, String email, String description, Team team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.description = description;
        this.team = team;
        this.opponents = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<String> getOpponents() {
        return opponents;
    }

    public void setOpponents(List<String> opponents) {
        this.opponents = opponents;
    }
}
