package pro.kensait.spring.mvc.bookstore.web.customer;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import pro.kensait.spring.mvc.bookstore.entity.Customer;
import pro.kensait.spring.mvc.bookstore.service.customer.CustomerExistsException;
import pro.kensait.spring.mvc.bookstore.service.customer.CustomerService;
import pro.kensait.spring.mvc.bookstore.web.cart.CartController;

public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(
            CartController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private HttpSession session;
    
    // アクションメソッド（顧客を登録する）
    public String register(CustomerParam customerParam) {

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

        session.setAttribute("customer", customer);
        return "success";
    }
}