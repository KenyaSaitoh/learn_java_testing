package pro.kensait.spring.bookstore.web.security;

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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(Customizer.withDefaults());

        http.formLogin(formLogin -> formLogin
                .loginPage("/").permitAll());

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers(HttpMethod.POST, "/processLogin").permitAll()
                .requestMatchers(HttpMethod.GET, "/processLogin").denyAll()
                .requestMatchers("/toRegister").permitAll()
                .requestMatchers(HttpMethod.POST, "/register").permitAll()
                .requestMatchers(HttpMethod.GET, "/register").denyAll() // GETの直接アクセスは禁止
                .anyRequest().authenticated());

        http.logout((logout) -> logout
            .logoutRequestMatcher(new AntPathRequestMatcher("/processLogout"))// デフォルトは"logout"
            .logoutSuccessUrl("/logoutSuccess") // デフォルトは"login?logout"
            .permitAll());

        // denyとpermitの順序関係を確認する!

        return http.build();
    }

    @Bean(name = "passwordEncoder")
    public PasswordEncoder BCPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth,
            UserService userService, PasswordEncoder passwordEncoder) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
    */
}