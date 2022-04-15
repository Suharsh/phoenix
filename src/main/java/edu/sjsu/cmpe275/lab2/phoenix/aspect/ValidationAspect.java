package edu.sjsu.cmpe275.lab2.phoenix.aspect;

import edu.sjsu.cmpe275.lab2.phoenix.dto.PlayerRequestDTO;
import edu.sjsu.cmpe275.lab2.phoenix.exception.InvalidEmailException;
import edu.sjsu.cmpe275.lab2.phoenix.exception.InvalidFirstNameException;
import edu.sjsu.cmpe275.lab2.phoenix.exception.InvalidLastNameException;
import edu.sjsu.cmpe275.lab2.phoenix.exception.InvalidPlayerId;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {
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

    @Before("execution(public * edu.sjsu.cmpe275.lab2.phoenix.service.PlayerService.updatePlayer(..))")
    public void validateUpdatePlayer(JoinPoint joinPoint){
        Object[] arguments = joinPoint.getArgs();
        PlayerRequestDTO player = (PlayerRequestDTO) arguments[0];

        if(isEmptyString(player.id))
            throw new InvalidPlayerId();
        else if(isEmptyString(player.firstName))
            throw new InvalidFirstNameException();
        else if(isEmptyString(player.lastName))
            throw new InvalidLastNameException();
        else if(isEmptyString(player.email))
            throw new InvalidEmailException();
    }

    @Before("execution(public * edu.sjsu.cmpe275.lab2.phoenix.service.PlayerService.deletePlayer(..))")
    public void validateDeletePlayer(JoinPoint joinPoint){
        Object[] arguments = joinPoint.getArgs();
        String id = (String) arguments[0];

        if(isEmptyString(id))
            throw new InvalidPlayerId();
    }

    private boolean isEmptyString(String s){
        return s == null || s.length() == 0 || s.trim().isEmpty();
    }
}
