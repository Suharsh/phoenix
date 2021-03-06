package edu.sjsu.cmpe275.lab2.phoenix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Player with same email id already exist")
public class PlayerAlreadyExistException extends RuntimeException{
}
