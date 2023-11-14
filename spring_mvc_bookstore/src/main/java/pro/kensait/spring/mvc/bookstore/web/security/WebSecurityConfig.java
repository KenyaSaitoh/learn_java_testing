package pro.kensait.spring.mvc.bookstore.web.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        // csrfを無効にする
        // http.csrf(AbstractHttpConfigurer::disable);
        
        // 有効にする
        // http.csrf(Customizer.withDefaults());
        // この場合は、各フォームに以下が必要
        /*
         * <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}"/>
         * https://spring.pleiades.io/spring-security/reference/servlet/exploits/csrf.html#csrf-integration-form
         */
        // http.csrf(AbstractHttpConfigurer::disable);
        /*
         * その理由は以下Chat GPTより
         * GETリクエストにCSRFトークンを含める必要は通常ありません。CSRF（Cross-Site Request Forgery）攻撃は
         * 主に「状態を変更する」操作（例えば、POST、PUT、DELETEリクエスト）に対してリスクがあります。
         * そのため、Spring SecurityのCSRF保護機能は、これらの種類のリクエストに対してトークンチェックを行います。
         */
        
        http.csrf(Customizer.withDefaults());

        /*
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

    */
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/").permitAll()
                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/login").denyAll()
                .requestMatchers("/toRegister").permitAll()
                .requestMatchers(HttpMethod.POST, "/register").permitAll()
                .requestMatchers(HttpMethod.GET, "/register").denyAll() // GETの直接アクセスは禁止
                .anyRequest().authenticated());

        
        // denyとpermitの順序関係を確認する!

        return http.build();
    }

    /*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth,
            UserService userService, PasswordEncoder passwordEncoder) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
    */
}