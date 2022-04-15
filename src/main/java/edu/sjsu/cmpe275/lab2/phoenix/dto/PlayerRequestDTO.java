package edu.sjsu.cmpe275.lab2.phoenix.dto;

public class PlayerRequestDTO {
    public String id;
    public String firstName;
    public String lastName;
    public String email;
    public String description;
    public String teamId;
    public String street;
    public String city;
    public String state;
    public String zip;

    public PlayerRequestDTO(){}
    public PlayerRequestDTO(String firstName, String lastName, String email, String description, String teamId, String street, String city, String state, String zip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.description = description;
        this.teamId = teamId;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
