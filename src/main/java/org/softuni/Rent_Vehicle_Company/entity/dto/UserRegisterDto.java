package org.softuni.Rent_Vehicle_Company.entity.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.lang.annotation.RequiredTypes;


@Getter
@Setter
public class UserRegisterDto {

    @Size(min = 3, max= 20,message = "- Username should be at least 3 symbols and maximum 20 symbols!")
    private String username;

    @Email()
    private String email;

    @Size(min = 3, max= 20,message ="- Password should be at least 3 symbols and maximum 20 symbols!")
    private String password;

    @Size(min = 3, max= 20, message ="- Password should be at least 3 symbols and maximum 20 symbols!")
    private String confirmPassword;


   @NotNull(message = "Please select one of the two options !")
    private String gender;

    public UserRegisterDto() {}
}
