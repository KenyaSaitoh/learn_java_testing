package pro.kensait.spring.bookstore.web.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import pro.kensait.spring.bookstore.apiclient.CustomerTO;
import pro.kensait.spring.bookstore.apiclient.CustomerApiClient;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(
            LoginController.class);

    @Autowired
    private CustomerApiClient customerApiClient;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProcessor tokenProcessor;

    @Autowired
    private HttpSession session;

    // アクションメソッド： ログイン
    @PostMapping("/processLogin")
    public String login(@Validated LoginParam loginParam, BindingResult errors) {
        logger.info("[ LoginController#processLogin ]");

        // 入力チェックを行い、エラーがあった場合はTopPageにフォワードする
        if (errors.hasErrors()) {
            logger.info("[ LoginController#processLogin ] 入力エラー");
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

        // サービスを呼び出し、顧客エンティティを取得する
        // → 存在していなかった場合はグローバルエラーを追加し、TopPageにフォワードする
        CustomerTO customer = null;
        try {
            customer = customerApiClient.queryCustomerByEmail(loginParam.email());
        } catch (UsernameNotFoundException unfe) {
            logger.info("[ LoginController#processLogin ] 顧客存在せずエラー");
            ObjectError error = new ObjectError("globarError",
                    new String[]{"error.email.not-exist"}, null, null);
            errors.addError(error);
            return "TopPage";
        }

        // 顧客のパスワード一致チェックを行う
        // → 不一致だった場合はグローバルエラーを追加し、TopPageにフォワードする
        boolean isMatch = passwordEncoder.matches(
                loginParam.password(),
                customer.password());
        if (! isMatch) {
            logger.info("[ LoginController#processLogin ] パスワード不一致エラー");
            ObjectError error = new ObjectError("globarError",
                    new String[]{"error.password.unmatch"}, null, null);
            errors.addError(error);
            return "TopPage";        
        }

        // 顧客エンティティをHTTPセッションに登録する
        session.setAttribute("customer", customer);

        // 認証済みトークンを生成し、SpringSecurityに対して明示的にログインを行う
        tokenProcessor.setUp(customer.email(), customer.password());

        // BookSelectPageにリダイレクトする
        // ※アクションを呼び出した上で画面フォワードしたいため、リダイレクトする
        return "redirect:/toSelect";
    }
}