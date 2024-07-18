package org.softuni.Rent_Vehicle_Company.config;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        // Показваме кои заявки позволяваме да се извикват за всички users.
        return httpSecurity.authorizeHttpRequests(

                        authorizeRequests ->
                                authorizeRequests
                                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()//Позволява да се свалят всички статични ресурси от всеки един в сайта.
                                        .requestMatchers("/","/about","/contact", "/register", "/login", "/car", "/login-error","/pricing", "/add-car").permitAll()//Позволява избраните уеб страници да бъдат достъпни за всички.
                                        .anyRequest().authenticated())// Всичко останало е забранено и се изисква ауторизация/
                .formLogin(formLogin ->
                        formLogin.loginPage("/login")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/", true)
                                .failureUrl("/login-error"))
                .logout(logout ->
                        // Logout url
                        logout.logoutUrl("/logout")
                                //After successful logout
                                .logoutSuccessUrl("/")
                                //end the session after logout
                                .invalidateHttpSession(true))
                .build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
