package com.jayfox.wigell_padel.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class IllegalAccessException extends RuntimeException {

    private String user;


    public IllegalAccessException(String user) {
        super(String.format("Illegal access, %s authorized but not allowed to manipulate resource",user));
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}