package org.softuni.Rent_Vehicle_Company.service;

import lombok.RequiredArgsConstructor;
import org.softuni.Rent_Vehicle_Company.model.entity.User;
import org.softuni.Rent_Vehicle_Company.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor


public class UserHelperService {
    private static final String ROLE_PREFIX = "ROLE_";
    private final UserRepository userRepository;



    //Взима целия user
    public User getUser() {
        return userRepository.findByUsername(getUserDetails().getUsername())
                .orElse(null);
    }



    //Проверява каква е ролята на дадения user
    public boolean hasRole(String role) {
        return getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(ROLE_PREFIX + role));
    }


    public UserDetails getUserDetails() {
        return (UserDetails) getAuthentication().getPrincipal();
    }


    //Проверява дали е логнат и ако да, връща true
    public boolean isAuthenticated() {
        // Spring security sets default user with Role ANONYMOUS when no user is authenticated.
        return !hasRole("ANONYMOUS");
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }



}
