package pro.kensait.spring.mvc.bookstore.service.customer;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pro.kensait.spring.mvc.bookstore.entity.Customer;
import pro.kensait.spring.mvc.bookstore.repository.CustomerRepository;
import pro.kensait.spring.mvc.bookstore.service.login.LoginRecord;

@Service
@Transactional
public class CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(
            CustomerService.class);

    @Autowired
    private CustomerRepository customerRepos;

    // ログイン可能かチェックする
    public boolean canLogin(LoginRecord loginRecord) {
        Optional<Customer> opt = customerRepos.findCustomerByEmail(loginRecord.email());
        if (opt.isEmpty()) return false;
        Customer customer = opt.get();
        if (! customer.getPassword().equals(loginRecord.password())) return false;
        return true;
    }

    // 顧客を登録する
    public Integer registerCustomer(Customer customer) { 
        // 生成したCustomerオブジェクトを保存する
        customerRepos.save(customer);

        //TODO
        // 重複チェックは？

        // 採番された顧客番号を返却する
        return customer.getCustomerId();
    }
}