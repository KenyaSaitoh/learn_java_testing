package pro.kensait.spring.mvc.bookstore.service.login;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pro.kensait.spring.mvc.bookstore.entity.Customer;
import pro.kensait.spring.mvc.bookstore.repository.CustomerRepository;

@Service
@Transactional
public class LoginService {
    private static final Logger logger = LoggerFactory.getLogger(
            LoginService.class);

    @Autowired
    private CustomerRepository customerRepos;

    // ログイン
    public Customer login(LoginTO loginTO) {
        // 入力された電子メールをキーにデータベースから顧客を検索する
        Optional<Customer> optional = customerRepos.findCustomerByEmail(loginTO.email());
        Customer customer = optional.get();

        // 顧客の存在チェックおよびパスワードの一致チェックを行う
        if (customer != null
                && loginTO.password().equals(customer.getPassword())) {
            return customer;
        } else {
            throw new RuntimeException("");
        }
    }
}