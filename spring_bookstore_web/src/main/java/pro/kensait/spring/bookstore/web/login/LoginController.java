package pro.kensait.spring.bookstore.web.login;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pro.kensait.spring.bookstore.apiclient.CustomerApiClient;
import pro.kensait.spring.bookstore.apiclient.CustomerNotFoundException;
import pro.kensait.spring.bookstore.apiclient.CustomerTO;

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
    private RequestCache requestCache;

    @Autowired
    private HttpSession session;

    // アクションメソッド：ログイン
    @PostMapping("/processLogin")
    public String processLogin(
            @Validated LoginParam loginParam,
            BindingResult errors,
            HttpServletRequest request,
            HttpServletResponse response) {
        logger.info("[ LoginController#processLogin ]");

        // 入力チェックを行い、エラーがあった場合はTopPageにフォワードする
        if (errors.hasErrors()) {
            logger.info("[ LoginController#processLogin ] 入力エラー");
            return "TopPage";
        }

        // サービスを呼び出し、顧客エンティティを取得する
        // → 存在していなかった場合はグローバルエラーを追加し、TopPageにフォワードする
        CustomerTO customer = null;
        try {
            customer = customerApiClient.queryCustomerByEmail(loginParam.email());
        } catch (CustomerNotFoundException cnfe) {
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

        // SavedRequestを取得し、ログイン前アクセスURLがあるかチェックする
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            // SavedRequestからログイン前アクセスURLを取り出し、リダイレクトする
            String redirectUrl = savedRequest.getRedirectUrl();
            try {
                response.sendRedirect(redirectUrl);
                // ・null値を返してメソッドを抜ける
                // ・レスポンスがコミットされた状態で別のリダイレクトが呼ばれてしまうため
                // ・else句を書いても結局"return null"は必要
                return null;
            } catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        }

        // ログイン前アクセスがなかった場合は、BookSelectPageにリダイレクトする
        // ※アクションを呼び出した上で画面遷移したいため、リダイレクトする
        return "redirect:/toSelect";
    }
}