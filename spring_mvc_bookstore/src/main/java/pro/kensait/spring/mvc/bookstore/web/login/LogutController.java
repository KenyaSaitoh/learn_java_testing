package pro.kensait.spring.mvc.bookstore.web.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class LogutController {
    private static final Logger logger = LoggerFactory.getLogger(
            LogutController.class);

    @Autowired
    private HttpSession session;

    // ログアウト
    @PostMapping("/logout")
    public String logout() {
        session.invalidate();
        return "FinishPage";
    }
}