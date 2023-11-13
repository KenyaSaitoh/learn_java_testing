package pro.kensait.spring.mvc.bookstore.service.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pro.kensait.spring.mvc.bookstore.entity.Customer;
import pro.kensait.spring.mvc.bookstore.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService  {
    private static final Logger logger = LoggerFactory.getLogger(
            CustomerService.class);

    @Autowired
    private CustomerRepository customerRepos;

    // 顧客を登録する
    public Customer register(Customer customer) throws CustomerExistsException { 
        // 生成したCustomerオブジェクトを保存する
        try {
            customerRepos.save(customer);
        } catch (DataIntegrityViolationException dve) {

            // CustomerExistsExceptionがチェック例外だと、ロールバックマークが付いて、
            // 違う例外（org.springframework.transaction.UnexpectedRollbackException）が送出される
            throw new CustomerExistsException("The customer already exists.", dve);
        }
        return customer;
    }

    public Customer findCustomer(String email) {
        Customer customer = customerRepos.findCustomerByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "No user found with this email address."));
        return customer;
    }
}