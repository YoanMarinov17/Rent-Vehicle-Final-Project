package org.softuni.Rent_Vehicle_Company.service.session;

import org.softuni.Rent_Vehicle_Company.model.entity.User;
import org.softuni.Rent_Vehicle_Company.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomUSerDetailsServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;

    public CustomUSerDetailsServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;

    }




    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        return userRepository.findByUsername(username)
                .map(this::mapToUserDetails).orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found !"));


    }


    private  UserDetails mapToUserDetails(User user) {
        return org.springframework.security.core.userdetails
                .User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(mapToGrantedAuthorities(user))
                .disabled(false)
                .build();
    }
    private List<GrantedAuthority> mapToGrantedAuthorities(User user){
                return user.getRoles().stream()
                        .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName().name())).collect(Collectors.toList());

    }


}
