package pro.kensait.spring.mvc.bookstore.web.login;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.support.SessionStatus;

import pro.kensait.spring.mvc.bookstore.entity.Customer;
import pro.kensait.spring.mvc.bookstore.service.login.LoginService;
import pro.kensait.spring.mvc.bookstore.service.login.LoginTO;
import pro.kensait.spring.mvc.bookstore.web.cart.CartController;

public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(
            CartController.class);

    @Autowired
    private LoginService loginService;

    @Autowired
    private HttpSession session;

    // アクションメソッド（ログイン）
    public String login(@Validated LoginParam loginParam) {
        LoginTO loginTO = new LoginTO(loginParam.email(), loginParam.password());
        Customer customer = loginService.login(loginTO);
        session.setAttribute("customer", customer);
        return "success";
    }

    // アクションメソッド（ログアウト）
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "LogoutPage.html";
    }
}