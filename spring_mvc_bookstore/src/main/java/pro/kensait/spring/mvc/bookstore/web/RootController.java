package pro.kensait.spring.mvc.bookstore.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import pro.kensait.spring.mvc.bookstore.web.login.LoginParam;

@Controller
@Configuration
public class RootController {
    private static final Logger logger = LoggerFactory.getLogger(
            RootController.class);

    // トップ画面に遷移する
    @GetMapping("/")
    public String index(@ModelAttribute("loginParam") LoginParam loginParam) {
        return "TopPage";
    }
}