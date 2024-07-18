package org.softuni.Rent_Vehicle_Company.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.softuni.Rent_Vehicle_Company.model.enums.UserRoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {



        @Column
        private String username;

        @Column
        private String email;

        @Column
        private String password;

        @Column
        private String gender;



        @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
        @Enumerated(EnumType.STRING)
        private List<Role> roles = new ArrayList<>();







        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private List<Vehicle> vehicles = new ArrayList<>();


        @ElementCollection(fetch = FetchType.EAGER)
        private List<GrantedAuthority> authorities;


        public void setRole(Role role){
                this.roles.add(role);
        }

        public User() {}

        }