package com.seventech.hospitalflow.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	JsonFilter jsonFilter () {
		JsonFilter jsonFilter = new JsonFilter();
		jsonFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
			// 登录成功的回调
			response.sendRedirect("/swagger-ui");
			// response.getWriter().write("login success!");
		});
		// 给自己定义的JSON登录Filter添加认证管理器
		jsonFilter.setAuthenticationManager(authenticationManager());
		// Spring Security 6 之前不用写，新版本因为自定义的这个过滤器破坏了原有配置，所以需要登录信息添加保存到session中的逻辑
		jsonFilter.setSecurityContextRepository(new HttpSessionSecurityContextRepository());
		return jsonFilter;
	}

	@Bean
	AuthenticationManager authenticationManager() {
		// DaoAuthenticationProvider 这个主要负责校验从数据库获取的用户名密码是否正确
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		return new ProviderManager(provider);
	}


	// 这个就是 Spring Security6 之后我们要使用的过滤器链，直接配置这个Bean就可以了
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		// 将我们的 JSON 登录 Filter 添加到用户名密码验证之前
		http.addFilterBefore(jsonFilter(), UsernamePasswordAuthenticationFilter.class);

		http
			// 移除某个过滤器
			.csrf(AbstractHttpConfigurer::disable)
			// 开启表单登录
			.formLogin(withDefaults())
			.authorizeHttpRequests((authorize) -> authorize
				// 这里配置的就是指经过过滤器链但是都不拦截的请求
				.requestMatchers("/home").permitAll()
				.anyRequest().authenticated())
			.httpBasic(withDefaults());
		// 这个方法就是返回默认的过滤器链
		return http.build();
	}

	/**
	 * 如果有一个请求不需要拦截：
	 * 1. 让这个请求不经过 Spring Security 过滤器链，前端资源
	 * 2. 让这个请求经过过滤器链，但是每个过滤器都不拦截，后端接口一般选择这个方式
	 * @return
	 */
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		// 这里配置的就是不经过过滤器链
		return web -> web.ignoring().requestMatchers("index.html","swagger-ui.html");
	}

	@Bean
	public UserDetailsService userDetailsService() {
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

		return new InMemoryUserDetailsManager(user, admin);

	}

}