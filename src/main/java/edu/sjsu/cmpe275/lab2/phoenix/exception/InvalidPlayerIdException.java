package edu.sjsu.cmpe275.lab2.phoenix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.BAD_REQUEST,reason = "Please enter a valid player id")
public class InvalidPlayerIdException extends RuntimeException{
}
