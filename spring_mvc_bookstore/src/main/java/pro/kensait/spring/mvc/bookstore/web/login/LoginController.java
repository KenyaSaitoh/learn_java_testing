package pro.kensait.spring.mvc.bookstore.web.login;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import pro.kensait.spring.mvc.bookstore.entity.Customer;
import pro.kensait.spring.mvc.bookstore.service.customer.CustomerService;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(
            LoginController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpSession session;

    // アクションメソッド： ログイン
    @PostMapping("/login")
    public String login(@Validated LoginParam loginParam, BindingResult errors) {
        logger.info("[ LoginController#login ]");

        if (errors.hasErrors()) {
            return "TopPage";
        }

        /*
        UserDetails customer = loginService.loadUserByUsername(loginParam.email());
        if (loginParam.password().equals(customer.getPassword())) {
            session.setAttribute("customer", customer);
            System.out.println("############ redirect:/toSelect");
            return "redirect:/toSelect"; // アクションからアクションを呼び出いのでリダイレクトする
        }
        return "TopPage";
        */

        Customer customer = null;
        try {
            customer = customerService.findCustomer(loginParam.email());
        } catch (UsernameNotFoundException unfe) {
            return "TopPage";
        }

        // 顧客の存在チェックおよびパスワードの一致チェックを行う
        boolean isMatch = passwordEncoder.matches(loginParam.password(),
                customer.getPassword());
        if (! isMatch) return "TopPage";

        session.setAttribute("customer", customer);

        List<GrantedAuthority> authorities = new ArrayList<>();

        Authentication authentication = UsernamePasswordAuthenticationToken
                .authenticated(customer.getEmail(),
                        customer.getPassword(),
                        authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        return "redirect:/toSelect"; // アクションからアクションを呼び出いのでリダイレクトする
    }
}