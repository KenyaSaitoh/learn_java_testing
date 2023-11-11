package pro.kensait.spring.mvc.bookstore.web.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import pro.kensait.spring.mvc.bookstore.service.login.LoginService;

@Controller
@Configuration
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(
            LoginController.class);

    @Autowired
    private LoginService loginService;

    @Autowired
    private HttpSession session;

    // ログイン
    @PostMapping("/login")
    public String login(@Validated LoginParam loginParam, BindingResult errors) {
        System.out.println("############ login");
        if (errors.hasErrors()) {
            System.out.println("############ hasErrors");
            return "TopPage";
        }

        UserDetails customer = loginService.loadUserByUsername(loginParam.email());
        if (loginParam.password().equals(customer.getPassword())) {
            session.setAttribute("customer", customer);
            System.out.println("############ redirect:/toSelect");
            return "redirect:/toSelect"; // アクションからアクションを呼び出いのでリダイレクトする
        }
        return "TopPage";
        
        /*
        LoginTO loginTO = new LoginTO(loginParam.email(), loginParam.password());
        try {
            Customer customer = loginService.login(loginTO);
            session.setAttribute("customer", customer);
            return "redirect:/toSelect"; // アクションからアクションを呼び出いのでリダイレクトする
        } catch(CustomerNotFoundException cne) {
            return "TopPage";
        }
        */
    }
}