package kr.hs.sdh.workbook1.config;

import kr.hs.sdh.workbook1.entity.Login;
import kr.hs.sdh.workbook1.entity.Role;
import kr.hs.sdh.workbook1.entity.Sign;
import kr.hs.sdh.workbook1.provider.AccountAuthenticationProvider;
import kr.hs.sdh.workbook1.repository.LoginRepository;
import kr.hs.sdh.workbook1.service.LoginService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

//@Controller
//@Service
//@Repository
//@Mapper
@Configuration
public class SecurityConfiguration {

    private final LoginService loginService;

    public SecurityConfiguration(LoginService loginService ) {
        this.loginService = loginService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .authenticationProvider(this.accountAuthenticationProvider())
                .authorizeHttpRequests(registry ->
                        registry.requestMatchers("/lotteria-example")
                                .authenticated()
                                .requestMatchers("login")
                                .anonymous()
                                .requestMatchers(
                                        "/fonts/**",
                                        "/images/**",
                                        "/styles/**",
                                        "/scripts/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .formLogin(
                        config ->
                                config.loginPage("/login")
                                        .loginProcessingUrl("/login-process")
                                        .defaultSuccessUrl("/lotteria-example")
                                        .usernameParameter("username")
                                        .passwordParameter("password")
                                        .failureHandler(((request, response, exception) -> {
                                            exception.printStackTrace();
                                            response.sendRedirect("/login");
                                        }))
                )
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

    @Bean
    AuthenticationProvider accountAuthenticationProvider() {

        AccountAuthenticationProvider accountAuthenticationProvider = new AccountAuthenticationProvider();

        accountAuthenticationProvider.setUserDetailsService(id -> {
            final Login login = this.loginService.getMember(id);

            if(login == null || id.isEmpty()) {
                final String message = "%s 아이디를 가진 사용자를 찾을 수 없습니다.".formatted(id);
                throw new UsernameNotFoundException(message);
            }

            final Set<Role> isAuthorized = loginService.getRoles(id);
            final String[] roleCodes = new String[isAuthorized.size()];
            int idx = 0;

            for(final Role role : isAuthorized) {
                roleCodes[idx++] = role.getCode();
            }


            return User.withUsername(login.getId())
                    .password(login.getPassword())
                    .roles(isAuthorized.toString())
                    .build();
        });
        accountAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());

        return accountAuthenticationProvider;

    }
}
