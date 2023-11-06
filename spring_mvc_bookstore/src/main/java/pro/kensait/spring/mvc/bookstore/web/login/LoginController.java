package pro.kensait.spring.mvc.bookstore.web.login;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import pro.kensait.spring.mvc.bookstore.entity.Customer;
import pro.kensait.spring.mvc.bookstore.service.login.LoginService;
import pro.kensait.spring.mvc.bookstore.service.login.LoginTO;
import pro.kensait.spring.mvc.bookstore.web.cart.CartController;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(
            CartController.class);

    @Autowired
    private LoginService loginService;

    @Autowired
    private HttpSession session;

    // 入力画面に遷移する
    @GetMapping("/")
    public String index(@ModelAttribute("loginParam") LoginParam loginParam) {
        return "TopPage";
    }

    // ログイン
    @PostMapping("/login")
    public String login(@Validated LoginParam loginParam,
            BindingResult errors) {
        if (errors.hasErrors()) {
            return "TopPage";
        }

        LoginTO loginTO = new LoginTO(loginParam.email(), loginParam.password());
        try {
            Customer customer = loginService.login(loginTO);
            session.setAttribute("customer", customer);
            return "redirect:/toSelect"; // アクションからアクションを呼び出いのでリダイレクトする
        } catch(RuntimeException re) {
            logger.info(re.toString());
            return "TopPage";
        }
    }

    // ログアウト
    @PostMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        session.invalidate();
        return "LogoutPage";
    }
}