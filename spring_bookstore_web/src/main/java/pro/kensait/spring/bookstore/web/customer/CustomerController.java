package pro.kensait.spring.bookstore.web.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import pro.kensait.spring.bookstore.apiclient.CustomerApiClient;
import pro.kensait.spring.bookstore.apiclient.CustomerTO;
import pro.kensait.spring.bookstore.web.login.TokenProcessor;

@Controller
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(
            CustomerController.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProcessor tokenProcessor;

    @Autowired
    private CustomerApiClient customerApiClient;

    @Autowired
    private HttpSession session;

    @GetMapping("/toRegister")
    public String toRegister(@ModelAttribute("customerParam")
            CustomerParam customerParam) {
        logger.info("[ CustomerController#toRegister ]");

        // CustomerInputPageにフォワードする
        return "CustomerInputPage";
    }

    // アクションメソッド： 顧客を登録する
    @PostMapping("/register")
    public String register(@Validated CustomerParam customerParam, BindingResult errors,
            Model model) {
        logger.info("[ CustomerController#register ]");

        // 住所に対する入力チェック（正しい都道府県名で始まっているか）を行い、
        // エラーがあった場合はBindingResultに追加する
        // ※他のエラーメッセージと一括して表示するため、画面遷移はしない
        if (! customerParam.address().isBlank() &&
                ! checkAddressPrefectures(customerParam.address())) {
            logger.info("[ CustomerController#register ] 住所入力エラー");
            errors.rejectValue("address", "error.address.prefecture");
        }

        // 入力チェックエラーがあった場合は元のCustomerInputPageにフォワードする
        if (errors.hasErrors()) {
            logger.info("[ CustomerController#register ] 入力エラー");
            return "CustomerInputPage";
        }

        // 顧客TOを生成する
        CustomerTO customer = new CustomerTO(
                null,
                customerParam.customerName(),
                passwordEncoder.encode(customerParam.password()),
                customerParam.email(),
                customerParam.birthday(),
                customerParam.address());

        // サービスを呼び出し、顧客エンティティを登録する
        //try {
        CustomerTO customer2 = customerApiClient.createCustomer(customer);
        session.setAttribute("customer", customer2);

        // 指定されたメールアドレスを持つ顧客がすでに存在する場合は、グローバルエラーを追加し、
        // 元のCustomerInputPageにフォワードする
    /*    
    } catch(CustomerExistsException cee) {
            logger.info("[ CustomerController#register ] 顧客重複エラー");
            ObjectError error = new ObjectError("globarError",
                    new String[]{"error.customer.exists"}, null, null);
            errors.addError(error);
            return "CustomerInputPage";
        }

        // 顧客エンティティをHTTPセッションに登録する
        session.setAttribute("customer", customer);

        // 認証済みトークンを生成し、SpringSecurityに対して明示的にログインを行う
        tokenProcessor.setUp(customer.email(), customer.password());

        // 要るか？
        //model.addAttribute("customer", customer);
        //model.addAttribute("customerId", customer.getCustomerId());

        /*
        List<GrantedAuthority> authorities = new ArrayList<>();
        User user = new User(customer.getCustomerName(), customer.getPassword(),
                authorities);
                */
        
        /*
        List<GrantedAuthority> authorities = new ArrayList<>();
        Authentication token =
                UsernamePasswordAuthenticationToken
                    .authenticated(customer.getEmail(),
                            customer.getPassword(),
                            authorities);

        SecurityContextHolder.getContext().setAuthentication(token);

        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        // authenticationManager.authenticate(authentication);

        SecurityContext context = SecurityContextHolder.createEmptyContext(); 
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        */

        // これはNG?
        // https://spring.pleiades.io/spring-security/reference/servlet/authentication/architecture.html


        /*
        SecurityContext context2 = SecurityContextHolder.getContext();
        Authentication authentication2 = context2.getAuthentication();
        String username = authentication2.getName();
        Object principal = authentication2.getPrincipal();
        System.out.println("EEEE" + username);
        System.out.println("EEEE" + principal);
        */

        // CustomerOutputPageにフォワードする
        return "CustomerOutputPage";
    }

    private boolean checkAddressPrefectures(String address) {
        for (String prefecture : PREFECTURES) {
            if (address.startsWith(prefecture)) {
                return true;
            }
        }
        return false;
    }
    
    private static final String[] PREFECTURES = {
            "北海道", "青森県", "岩手県", "宮城県", "秋田県", 
            "山形県", "福島県", "茨城県", "栃木県", "群馬県", 
            "埼玉県", "千葉県", "東京都", "神奈川県", "新潟県", 
            "富山県", "石川県", "福井県", "山梨県", "長野県", 
            "岐阜県", "静岡県", "愛知県", "三重県", "滋賀県", 
            "京都府", "大阪府", "兵庫県", "奈良県", "和歌山県", 
            "鳥取県", "島根県", "岡山県", "広島県", "山口県", 
            "徳島県", "香川県", "愛媛県", "高知県", "福岡県", 
            "佐賀県", "長崎県", "熊本県", "大分県", "宮崎県", 
            "鹿児島県", "沖縄県"
        };
}