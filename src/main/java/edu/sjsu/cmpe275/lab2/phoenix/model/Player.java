package edu.sjsu.cmpe275.lab2.phoenix.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
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
    @NotNull
    @Column(name="first_name")
    private String firstName;
    @NotNull
    @Column(name="last_name")
    private String lastName;
    @NotNull
    @Column(name="email")
    private String email;
    @Column(name="description")
    private String description;

    @Embedded
    private Address address;

    @JsonIgnoreProperties({"players","address"})
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="team_id", referencedColumnName = "id")
    private Team team;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"opponentsOf","team","opponents","address"})
    @JoinTable(name="Opponent",
            joinColumns={@JoinColumn(name="player_id",referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="opponent_id",referencedColumnName="id")}
    )
    private List<Player> opponents;

    @ManyToMany(mappedBy = "opponents",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"opponents","team","opponentsOf"})
    @JsonIgnore
    private List<Player> opponentsOf;

    public Player(){

    }

//    @PreRemove
//    public void beforeDelete(){
//        team
//    }

    public Player(String firstName, String lastName, String email, String description, Team team, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.description = description;
        this.team = team;
        this.opponents = new ArrayList<>();
        this.address = address;
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

    public List<Player> getOpponents() {
        return opponents;
    }

    public void setOpponents(List<Player> opponents) {
        this.opponents = opponents;
    }

    public List<Player> getOpponentsOf() {
        return opponentsOf;
    }

    public void setOpponentsOf(List<Player> opponentsOf) {
        this.opponentsOf = opponentsOf;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
