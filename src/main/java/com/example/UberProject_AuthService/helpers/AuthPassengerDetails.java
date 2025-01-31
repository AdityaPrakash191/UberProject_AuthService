package com.example.UberProject_AuthService.helpers;

import com.example.UberProject_AuthService.models.Passenger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

// why we need this?
//in spring security whenever a authentication happens, spring security perform authentication on object of UserDetails Interface.
// because spring security works on UserDetails polymorphic type for auth


public class AuthPassengerDetails extends Passenger implements UserDetails {

    private String username;

    private String password;

    public AuthPassengerDetails(Passenger passenger){
        this.username = passenger.getEmail();
        this.password = passenger.getPassword();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword(){
        return this.password;
    }


}
