package edu.sjsu.cmpe275.lab2.phoenix.aspect;

import edu.sjsu.cmpe275.lab2.phoenix.dto.PlayerRequestDTO;
import edu.sjsu.cmpe275.lab2.phoenix.exception.InvalidEmailException;
import edu.sjsu.cmpe275.lab2.phoenix.exception.InvalidFirstNameException;
import edu.sjsu.cmpe275.lab2.phoenix.exception.InvalidLastNameException;
import edu.sjsu.cmpe275.lab2.phoenix.exception.InvalidPlayerIdException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {

    /***
     * Validates the create player request; throws respective exceptions if the required parameters like firstName,
     * lastName and email are empty or null
     *
     * @param joinPoint         provides access to arguments of the createPlayer method call
     */
    @Before("execution(public * edu.sjsu.cmpe275.lab2.phoenix.service.PlayerService.createPlayer(..))")
    public void validateCreatePlayer(JoinPoint joinPoint){
        Object[] arguments = joinPoint.getArgs();
        PlayerRequestDTO player = (PlayerRequestDTO) arguments[0];
        if(isEmptyString(player.firstName))
            throw new InvalidFirstNameException();
        else if(isEmptyString(player.lastName))
            throw new InvalidLastNameException();
        else if(isEmptyString(player.email))
            throw new InvalidEmailException();
    }

    /***
     * Validates the update player request; throws respective exceptions if the required parameters like id, firstName,
     * lastName and email are empty or null
     *
     * @param joinPoint         provides access to arguments of the updatePlayer method call
     */
    @Before("execution(public * edu.sjsu.cmpe275.lab2.phoenix.service.PlayerService.updatePlayer(..))")
    public void validateUpdatePlayer(JoinPoint joinPoint){
        Object[] arguments = joinPoint.getArgs();
        PlayerRequestDTO player = (PlayerRequestDTO) arguments[0];

        if(isEmptyString(player.id))
            throw new InvalidPlayerIdException();
        else if(isEmptyString(player.firstName))
            throw new InvalidFirstNameException();
        else if(isEmptyString(player.lastName))
            throw new InvalidLastNameException();
        else if(isEmptyString(player.email))
            throw new InvalidEmailException();
    }
    /***
     * Validates delete player request; throws exceptions if the player id is empty or null
     *
     * @param joinPoint         provides access to arguments of the deletePlayer method call
     */
    @Before("execution(public * edu.sjsu.cmpe275.lab2.phoenix.service.PlayerService.deletePlayer(..))")
    public void validateDeletePlayer(JoinPoint joinPoint){
        Object[] arguments = joinPoint.getArgs();
        String id = (String) arguments[0];

        if(isEmptyString(id))
            throw new InvalidPlayerIdException();
    }

    /***
     * Checks if the given string is empty or null
     *
     * @param s     the string which has to checked if it is empty, null, or simply whitespaces
     * @return      true if the string is empty; false otherwise;
     */
    private boolean isEmptyString(String s){
        return s == null || s.length() == 0 || s.trim().isEmpty();
    }
}
