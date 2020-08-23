package com.example.todoapp.config;

import com.example.todoapp.infra.basicauthusermanagement.JpaUserRepositoryInterface;
import com.example.todoapp.infra.basicauthusermanagement.MyUserDetailsService;
import com.example.todoapp.infra.basicauthusermanagement.UserJpa;
import com.example.todoapp.infra.socialauthn.SocialUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(
                    "/public/**",
                    "/",
                    "/listItems",
                    "/deleteItems",
                    "/item/**",
                    "/favicon.ico",
                    "/*.css",
                    "/*.js",
                    "/currentUser",
                    "/error",
                    "/logout",
                    "/webjars/**",
                    "/oauth2/authorization/**"

            ).permitAll()
            .antMatchers(POST, "/createItem").permitAll()
            .anyRequest().authenticated()
            .and()
            .httpBasic()
            .and()
            .formLogin()
                .loginPage("/login.html")
                .failureUrl("/login-error.html")
            .permitAll()
            .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .permitAll()
            .and()
            .oauth2Login()
                .userInfoEndpoint(config -> config
                        .customUserType(GitHubOAuth2User.class, "github")
                .oidcUserService(oidcUserRequest -> {
                    System.out.println();
                    return new DefaultOidcUser(
                            Collections.singleton(new OAuth2UserAuthority(Map.of("toto", "tata"))),
                            oidcUserRequest.getIdToken(),
                            "name");
                }))
                .defaultSuccessUrl("/", true)
                .successHandler(oauthLoginSuccessHandler());
        // @formatter:on
    }

    @Autowired
    private SocialUserRepository socialUserRepository;

    @Bean
    AuthenticationSuccessHandler oauthLoginSuccessHandler() {
        return new OauthLoginSuccessHandler(socialUserRepository);
    }

    @Autowired
    @Qualifier("myUserDetailsService")
    private UserDetailsService userDetailsService;
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                userDetailsService(userDetailsService)
                .passwordEncoder(encoder())
                .and()
                .authenticationProvider(authenticationProvider(encoder()))
                .jdbcAuthentication()
                .dataSource(dataSource);
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(PasswordEncoder encoder) {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder);
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder(11);
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    UserDetailsService myUserDetailsService(JpaUserRepositoryInterface jpaUserRepositoryInterface) {
        return new MyUserDetailsService(jpaUserRepositoryInterface);
    }
}



