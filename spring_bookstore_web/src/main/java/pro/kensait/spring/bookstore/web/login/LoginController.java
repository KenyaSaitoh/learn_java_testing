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
import pro.kensait.spring.bookstore.entity.Customer;
import pro.kensait.spring.bookstore.service.customer.CustomerService;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(
            LoginController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProcessor tokenProcessor;

    @Autowired
    private HttpSession session;

    // アクションメソッド： ログイン
    @PostMapping("/login")
    public String login(@Validated LoginParam loginParam, BindingResult errors) {
        logger.info("[ LoginController#login ]");

        // 入力チェックを行い、エラーがあった場合はTopPageに遷移する
        if (errors.hasErrors()) {
            logger.info("[ LoginController#login ] 入力エラー");
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
        // 存在していなかった場合は、エラー処理を行う
        Customer customer = null;
        try {
            customer = customerService.findCustomer(loginParam.email());
        } catch (UsernameNotFoundException unfe) {
            logger.info("[ LoginController#login ] 顧客存在せずエラー");
            ObjectError error = new ObjectError("顧客存在せず", "指定されたメールアドレスは存在しません");
            errors.addError(error);
            return "TopPage";
        }

        // 顧客のパスワード一致チェックを行う
        boolean isMatch = passwordEncoder.matches(loginParam.password(), customer.getPassword());
        if (! isMatch) {
            logger.info("[ LoginController#login ] パスワード不一致エラー");
            ObjectError error = new ObjectError("パスワード不一致", "指定されたパスワードが間違っているようです");
            errors.addError(error);
            return "TopPage";        
        }

        // 顧客エンティティをHTTPセッションに登録する
        session.setAttribute("customer", customer);

        // 認証済みトークンを生成し、SpringSecurityに対して明示的にログインを行う
        tokenProcessor.setUp(customer.getEmail(), customer.getPassword());

        // BookSelectPageにリダイレクトする
        // アクションを呼び出した上で画面遷移したいため、リダイレクトする
        return "redirect:/toSelect";
    }
}