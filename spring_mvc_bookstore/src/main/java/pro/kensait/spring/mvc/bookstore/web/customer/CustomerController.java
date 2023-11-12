package pro.kensait.spring.mvc.bookstore.web.customer;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import pro.kensait.spring.mvc.bookstore.entity.Customer;
import pro.kensait.spring.mvc.bookstore.service.customer.CustomerExistsException;
import pro.kensait.spring.mvc.bookstore.service.customer.CustomerService;

@Controller
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(
            CustomerController.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private HttpSession session;

    @GetMapping("/toRegister")
    public String toRegister(@ModelAttribute("customerParam")
            CustomerParam customerParam) {
        return "CustomerRegisterInputPage";
    }

    // 顧客を登録する
    @PostMapping("/register")
    public String register(@Validated CustomerParam customerParam, BindingResult errors,
            Model model) {
        System.out.println("RRRRRRRRRRR");
        if (errors.hasErrors()) {
            return "CustomerRegisterInputPage";
        }

        Customer customer = new Customer(
                customerParam.customerName(),
                passwordEncoder.encode(customerParam.password()),
                customerParam.email(),
                customerParam.birthday(),
                customerParam.address());

        try {
            customerService.register(customer);
        } catch(CustomerExistsException cee) {

        }

        logger.info(customer.toString());
        session.setAttribute("customer", customer);
        model.addAttribute("customer", customer);
        model.addAttribute("customerId", customer.getCustomerId());

        /*
        List<GrantedAuthority> authorities = new ArrayList<>();
        User user = new User(customer.getCustomerName(), customer.getPassword(),
                authorities);
                */
        List<GrantedAuthority> authorities = new ArrayList<>();

        Authentication authentication =
                UsernamePasswordAuthenticationToken
                    .authenticated(customer.getEmail(),
                            customer.getPassword(),
                            authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        // authenticationManager.authenticate(authentication);

        /*
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

        return "CustomerRegisterOutputPage";
    }
}