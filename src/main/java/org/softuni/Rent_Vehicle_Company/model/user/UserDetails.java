package org.softuni.Rent_Vehicle_Company.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.UUID;

public class UserDetails extends User {




    public UserDetails(
            Long id,
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }


}
