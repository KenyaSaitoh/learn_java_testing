package pro.kensait.spring.mvc.bookstore.web.customer;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pro.kensait.spring.mvc.bookstore.entity.Customer;
import pro.kensait.spring.mvc.bookstore.service.customer.CustomerExistsException;
import pro.kensait.spring.mvc.bookstore.service.customer.CustomerService;
import pro.kensait.spring.mvc.bookstore.web.cart.CartController;

@Controller
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(
            CartController.class);

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
        if (errors.hasErrors()) {
            return "CustomerRegisterInputPage";
        }

        Customer customer = new Customer(
                customerParam.customerName(),
                customerParam.password(),
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
        return "CustomerRegisterOutputPage";
    }
}