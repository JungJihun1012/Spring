package kr.hs.sdh.workbook1.config;

import kr.hs.sdh.workbook1.entity.Login;
import kr.hs.sdh.workbook1.entity.Sign;
import kr.hs.sdh.workbook1.service.LoginService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

//@Controller
//@Service
//@Repository
//@Mapper
@Configuration
public class SecurityConfiguration {

    private final LoginService loginService;

    public SecurityConfiguration(LoginService loginService) {
        this.loginService = loginService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(registry -> registry.requestMatchers("/lotteria-example").authenticated() .requestMatchers("login").anonymous() .requestMatchers("/fonts/**", "/images/**", "/styles/**", "/scripts/**").permitAll() .anyRequest().authenticated())
                .formLogin(
                        config ->
                                config.loginPage("/login")
                                        .loginProcessingUrl("/login-process")
                                        .defaultSuccessUrl("/lotteria-example")
                                        .usernameParameter("username")
                                        .passwordParameter("password")
                )
                .userDetailsService(id -> {
                    final Login login = this.loginService.getMember(id);
                    return User.withUsername(login.getId())
                            .password(login.getPassword())
                            .build();
                })
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.contentEquals(rawPassword);
            }

        };
    }
}
