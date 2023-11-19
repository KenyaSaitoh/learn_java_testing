package pro.kensait.spring.bookstore.web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private static final Logger logger = LoggerFactory.getLogger(
            WebSecurityConfig.class);

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        logger.info("[ WebSecurityConfig#securityFilterChain ]");

        http.csrf(Customizer.withDefaults());

        http.formLogin(formLogin -> formLogin
                .loginPage("/").permitAll()); // 認証未済だと自動的に遷移する

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers(HttpMethod.POST, "/processLogin").permitAll()
                .requestMatchers(HttpMethod.GET, "/toRegister").permitAll()
                .requestMatchers(HttpMethod.GET,
                        "/processLogin",
                        "/register",
                        "/search",
                        "/search2",
                        "/addBook",
                        "/removeBook",
                        "/clear",
                        "/fix",
                        "/order",
                        "/processLogout").denyAll() // GETの直接アクセスは禁止
                .anyRequest().authenticated());

        http.logout((logout) -> logout
            .logoutRequestMatcher(new AntPathRequestMatcher("/processLogout"))// デフォルトは"logout"
            .logoutSuccessUrl("/logoutSuccess") // デフォルトは"login?logout"
            .permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        logger.info("[ WebSecurityConfig#passwordEncoder ]");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RequestCache requestCache(){
        logger.info("[ WebSecurityConfig#requestCache ]");
        return new HttpSessionRequestCache();
    }
}