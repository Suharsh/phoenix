package edu.sjsu.cmpe275.lab2.phoenix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Team not found")  // 404
public class TeamNotFoundException extends RuntimeException {
}
