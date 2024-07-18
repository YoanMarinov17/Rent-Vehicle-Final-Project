package org.softuni.Rent_Vehicle_Company.service.session;

import org.softuni.Rent_Vehicle_Company.model.entity.Role;
import org.softuni.Rent_Vehicle_Company.model.entity.User;
import org.softuni.Rent_Vehicle_Company.model.enums.UserRoleEnum;
import org.softuni.Rent_Vehicle_Company.model.user.CustomUserDetails;
import org.softuni.Rent_Vehicle_Company.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUSerDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    public CustomUSerDetailsService(UserRepository userRepository) {

        this.userRepository = userRepository;

    }


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        return userRepository.findByUsername(username)
                .map(CustomUSerDetailsService::mapToUserDetails)
                .orElseThrow(()
                        -> new UsernameNotFoundException("Username " + username + " not found !"));

    }


    private  static UserDetails mapToUserDetails(User user) {
        return  new CustomUserDetails(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(Role::getRole).map(CustomUSerDetailsService::mapToGrantedAuthorities).toList()
                );
    }
    private static GrantedAuthority mapToGrantedAuthorities(UserRoleEnum role){
        return new SimpleGrantedAuthority(
                "ROLE_" + role
        );

    }


}
