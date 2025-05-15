package com.iovu.iovuback.config;

import com.iovu.iovuback.security.handler.CustomAuthenticationFailureHandler;
import com.iovu.iovuback.security.handler.CustomAuthenticationSuccessHandler;
import com.iovu.iovuback.security.service.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.iovu.iovuback.oauth.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //kang
    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;

    @Autowired
    private CustomAuthenticationFailureHandler failureHandler;

    //kim
    private final CustomOAuth2UserService customOAuth2UserService;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
  
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
            .logout(logout -> logout
                    .logoutUrl("/logout") // 기본값
                    .logoutSuccessHandler((request, response, authentication) -> {
//                        response.sendRedirect("https://nid.naver.com/nidlogin.logout?returl=http://localhost:8081/logoutSuccess");
                          response.sendRedirect("/logoutBridge");
                    })
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
            )
            .oauth2Login(oauth2 -> oauth2
                .userInfoEndpoint(userInfo -> userInfo
                    .userService(customOAuth2UserService)
                )
                .defaultSuccessUrl("/home") //리디렉션 경로 설정
            );
        return http.build();
    }
}
