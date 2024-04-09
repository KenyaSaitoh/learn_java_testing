package pro.kensait.spring.bookstore.web.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
    private static final Logger logger = LoggerFactory.getLogger(
            LogoutController.class);

    // アクションメソッド：ログアウト
    @GetMapping("/logoutSuccess")  // このURLにリダイレクトされるのでGET
    public String logoutSuccess() {
        logger.info("[ LogoutController#logoutSuccess ]");

        return "FinishPage";
    }
}