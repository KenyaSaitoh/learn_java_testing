package pro.kensait.spring.mvc.bookstore.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import pro.kensait.spring.mvc.bookstore.service.login.LoginService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    
    @Autowired
    MyAuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.formLogin(form -> {
            form
                    .loginPage("/") // Login page will be accessed through this endpoint. We will create a controller method for this.
                    .loginProcessingUrl("/login") // This endpoint will be mapped internally. This URL will be our Login form post action.
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .permitAll() // We re permitting all for login page
                    .defaultSuccessUrl("/toSelect") // If the login is successful, user will be redirected to this URL.
                    //.failureUrl("/"); // If the user fails to login, application will redirect the user to this endpoint
                    .failureForwardUrl("/"); // リダイレクトではなくもとの入力画面にフォワードする（リダイレクトだとバリデーションの結果が表示されない）
        });
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/").permitAll()
                .requestMatchers("/toRegister").permitAll()
                .requestMatchers(HttpMethod.POST, "/register").permitAll()
                .requestMatchers(HttpMethod.GET, "/register").denyAll() // GETの直接アクセスは禁止
                .anyRequest().authenticated());

        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        http.addFilterBefore(filter, CustomAuthenticationFilter.class);

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth,
            LoginService loginService, PasswordEncoder passwordEncoder) throws Exception {
        auth.userDetailsService(loginService).passwordEncoder(passwordEncoder);
    }

    /*
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        System.out.println("WWWWWW" + authenticationConfiguration);
        return authenticationConfiguration.getAuthenticationManager();
    }
    */
}