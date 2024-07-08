package org.softuni.Rent_Vehicle_Company.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softuni.Rent_Vehicle_Company.model.enums.UserRoleEnum;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {


    @Enumerated(value = EnumType.STRING)
    @NotNull
    @Column(unique = true)
    private UserRoleEnum name;


    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "roles")
    private List<User> users = new ArrayList<>();
}
