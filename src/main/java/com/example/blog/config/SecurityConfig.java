//package com.example.blog.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//
//public class SecurityConfig {
//    @Bean  // 이 메서드는 사용자 정보를 메모리에 저장하기 위한 UserDetailsService를 생성합니다.
//    public UserDetailsService userDetailsService() {
//        // 사용자 정보를 생성합니다.
//        // "user"라는 아이디와 암호화된 "password"라는 비밀번호를 설정합니다.
//        var user = User.withUsername("user")
//                .password(passwordEncoder().encode("password"))
//                .roles("USER")
//                .build();
//
//        // 메모리에서 사용자 정보를 관리하는 InMemoryUserDetailsManager를 반환합니다.
//        return new InMemoryUserDetailsManager(user);
//    }
//
//    @Bean  // 이 메서드는 비밀번호를 암호화하기 위한 PasswordEncoder를 생성합니다.
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();  // BCryptPasswordEncoder는 비밀번호를 안전하게 암호화합니다.
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().permitAll()// 모든 요청을 인증 없이 허용
//
//                )
//                .csrf(csrf -> csrf.disable());  // CSRF 보호를 비활성화
//
//        return http.build();
//    }
//
//}
//
//
