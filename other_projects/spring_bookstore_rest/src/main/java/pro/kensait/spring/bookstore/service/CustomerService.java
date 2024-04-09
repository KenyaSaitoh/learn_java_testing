package pro.kensait.spring.bookstore.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pro.kensait.spring.bookstore.entity.Customer;
import pro.kensait.spring.bookstore.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService  {
    private static final String CUSTOMER_NOT_FOUND_MESSAGE =
            "指定されたメールアドレスは存在しません";
    private static final String CUSTOMER_EXISTS_MESSAGE =
            "指定されたメールアドレスはすでに存在します";

    private static final Logger logger = LoggerFactory.getLogger(
            CustomerService.class);

    @Autowired
    private CustomerRepository customerRepos;

    // サービスメソッド：顧客を取得する（一意キーからの条件検索）
    public Customer getCustomerById(Integer customerId) {
        logger.info("[ CustomerService#getCustomerById ]");

        // メールアドレスから顧客エンティティを検索する
        Customer customer = customerRepos.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                        CUSTOMER_NOT_FOUND_MESSAGE));
        return customer;
    }

    // サービスメソッド：顧客を取得する（一意キーからの条件検索）
    public Customer getCustomerByEmail(String email) {
        logger.info("[ CustomerService#getCustomerByEmail ]");

        // メールアドレスから顧客エンティティを検索する
        Customer customer = customerRepos.findCustomerByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(
                        CUSTOMER_NOT_FOUND_MESSAGE));
        return customer;
    }

    // サービスメソッド：顧客リストを取得する（誕生日からの条件検索）
    public List<Customer> searchCustomersFromBirthday(LocalDate from) {
        logger.info("[ CustomerService#searchCustomersFromBirthday ]");

        // 誕生日開始日から顧客エンティティを検索する
        List<Customer> customers = customerRepos.searchCustomersFromBirthday(from);
        return customers;
    }

    // サービスメソッド：顧客を新規登録する
    public Customer registerCustomer(Customer customer) throws CustomerExistsException { 
        logger.info("[ CustomerService#registerCustomer ]");

        // 受け取った顧客エンティティを保存する
        try {
            customerRepos.save(customer);

        // 一意制約違反（メールアドレス）が発生した場合
        } catch (DataIntegrityViolationException dve) {

            // CustomerExistsExceptionがチェック例外だと、ロールバックマークが付いて、
            // 違う例外（org.springframework.transaction.UnexpectedRollbackException）が送出される
            throw new CustomerExistsException(CUSTOMER_EXISTS_MESSAGE, dve);
        }
        return customer;
    }

    // サービスメソッド：顧客を上書き登録する
    public void replaceCustomer(Customer customer)
            throws CustomerExistsException { 
        logger.info("[ CustomerService#replaceCustomer ]");

        // 受け取った顧客エンティティを保存する
        try {
            customerRepos.save(customer);

        // 一意制約違反（メールアドレス）が発生した場合
        } catch (DataIntegrityViolationException dve) {
            customerRepos.delete(customer);
            customerRepos.save(customer);
        }
    }

    // サービスメソッド：顧客を上書き登録する
    public void deleteCustomer(Integer customerId) {
        logger.info("[ CustomerService#deleteCustomer ]");

        // 受け取った顧客IDをキーにエンティティを削除する
        customerRepos.deleteById(customerId);
    }
}