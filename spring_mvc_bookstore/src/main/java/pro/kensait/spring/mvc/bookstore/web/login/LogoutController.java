package pro.kensait.spring.mvc.bookstore.web.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class LogoutController {
    private static final Logger logger = LoggerFactory.getLogger(
            LogoutController.class);

    @Autowired
    private HttpSession session;

    // アクションメソッド： ログアウト
    @GetMapping("/logoutSuccess")  // このURLにリダイレクトされるのでGET
    public String logoutSuccess() {
        logger.info("LogoutController#logoutSuccess");
        session.invalidate();
        return "FinishPage";
    }
}