package pro.kensait.spring.mvc.bookstore.service.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
        customerRepos.save(customer);

        //TODO
        // 重複チェックは？

        return customer;
    }


}