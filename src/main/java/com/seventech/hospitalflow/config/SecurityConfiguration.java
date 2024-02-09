package com.seventech.hospitalflow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration // Spring中配置注解
@EnableWebSecurity // 启用Spring-Security来安全校验
public class SecurityConfiguration {

    @Bean // 声明Bean对象，Spring会帮我们new出来，而且是单例的
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .formLogin(withDefaults());
        // @formatter:on
        return http.build();
    }

    // @formatter:off
    @Bean
    public UserDetailsService users() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        UserDetails user = User.builder()
                .username("seven")
                .password(encoder.encode("111111")) // 这里换成你的明文密码
                .roles("ADMIN","USER")
                .build();

        UserDetails admin = User.builder()
                .username("casy")
                .password(encoder.encode("111111")) // 这里换成你的明文密码
                .roles("USER")
                .build();

        // 配置Lin这个用户
        UserDetails partner = User.builder()
                .username("lin")
                .password(encoder.encode("111111")) // 这里换成你的明文密码
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user, admin,partner);

    }
    // @formatter:on

}

