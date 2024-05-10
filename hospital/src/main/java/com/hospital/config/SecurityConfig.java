package com.hospital.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests(authorize -> authorize
                        //모든 사요자가 로그인(인증) 없이 접근할 수 있도록 설정
                        .requestMatchers("/css/**", "/js/**", "/img/**", "/images/**", "/fonts/**").permitAll()
                        .requestMatchers("/", "/members/**","/doctors/**","/reserve/**").permitAll()
                        .requestMatchers("/favicon.ico", "/error").permitAll()
                        //관리자만 접근 가능하도록 설정
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        //그 외의 페이지는 모두 로그인(인증)을 해야 한다.
                        .anyRequest().authenticated() //CAE에 있는 commence() 메소드를 실행해줌
                )
                //2.로그인에 관한 설정
                .formLogin(formLogin -> formLogin
                        .loginPage("/members/login") //로그인 페이지 url
                        .defaultSuccessUrl("/") //로그인 성공시 이동할 페이지 url
                        .usernameParameter("email") //★로그인시 id로 사용할 파라미터 이름(내 사이트 맞는걸로 바꿔줘야된다)
                        .failureUrl("/members/login/error")//로그인 실패시 페이지이동할 url
                )
                .logout(logout->logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                        .logoutSuccessUrl("/")
                        )
                .exceptionHandling(handling->handling
                        .authenticationEntryPoint(new CustomerAuthenticationEntryPoint())
                )
                .rememberMe(Customizer.withDefaults());
        return httpSecurity.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();
    }
}
