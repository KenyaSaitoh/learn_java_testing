package pro.kensait.spring.bookstore.web.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import pro.kensait.spring.bookstore.entity.Customer;
import pro.kensait.spring.bookstore.service.customer.CustomerExistsException;
import pro.kensait.spring.bookstore.service.customer.CustomerService;
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
    private CustomerService customerService;

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

        // 入力チェックを行い、エラーがあった場合は元のCustomerInputPageにフォワードする
        if (errors.hasErrors()) {
            logger.info("[ CustomerController#register ] 入力エラー");
            return "CustomerInputPage";
        }

        // 顧客エンティティを生成する
        Customer customer = new Customer(
                customerParam.customerName(),
                passwordEncoder.encode(customerParam.password()),
                customerParam.email(),
                customerParam.birthday(),
                customerParam.address());

        // サービスを呼び出し、顧客エンティティを登録する
        // → 指定されたメールアドレスを持つ顧客が既に存在する場合は、グローバルエラーを追加し、
        // 元のCustomerInputPageにフォワードする
        try {
            customerService.register(customer);
        } catch(CustomerExistsException cee) {
            logger.info("[ CustomerController#register ] 顧客重複エラー");
            ObjectError error = new ObjectError("globarError", "すでに顧客は登録されています");
            errors.addError(error);
            return "CustomerInputPage";
        }

        // 顧客エンティティをHTTPセッションに登録する
        session.setAttribute("customer", customer);

        // 認証済みトークンを生成し、SpringSecurityに対して明示的にログインを行う
        tokenProcessor.setUp(customer.getEmail(), customer.getPassword());

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
}