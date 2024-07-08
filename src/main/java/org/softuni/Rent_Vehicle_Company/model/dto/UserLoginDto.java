package org.softuni.Rent_Vehicle_Company.model.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDto {


        @NotBlank
        @Size(min = 3, max = 20,message = "Username should be at least 3 symbols !")
        private String username;

        @NotBlank
        @Size(min = 3, max = 20,message = "Password should be at least 3 symbols !")
        private String password;


        public UserLoginDto() {}

}
