package pro.kensait.spring.bookstore.api;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pro.kensait.spring.bookstore.entity.Customer;
import pro.kensait.spring.bookstore.service.customer.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerApi  {
    private static final Logger logger = LoggerFactory.getLogger(
            CustomerApi.class);

    @Autowired
    private CustomerService customerService;

    // APIメソッド： 顧客を取得する（主キー検索）
    @GetMapping(value = "/{customerId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomerById(
            @PathVariable(name = "customerId", required = true)
            Integer customerId) {
        logger.info("[ CustomerAPI#getCustomerById ]");

        // メールアドレスから顧客エンティティを検索する
        Customer customer = customerService.getCustomerById(customerId);

        // 顧客エンティティ（ボディ）とHTTPステータスOKを持つResponseEntityを返す
        return ResponseEntity.ok(customer);
    }

    // APIメソッド： 顧客を取得する（一意キーからの条件検索）
    @GetMapping(value = "/query_email",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> queryCustomerByEmail(
            @RequestParam(name = "email", required = true)
            String email) {
        logger.info("[ CustomerAPI#queryCustomerByEmail ]");

        // メールアドレスから顧客エンティティを検索する
        Customer customer = customerService.getCustomerByEmail(email);

        // 顧客エンティティ（ボディ）とHTTPステータスOKを持つResponseEntityを返す
        return ResponseEntity.ok(customer);
    }

    // APIメソッド： 顧客リストを取得する（誕生日からの条件検索）
    @GetMapping(value = "/query_birthday",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> queryCustomersFromBirthday(
            @RequestParam(name = "birthday", required = true)
            LocalDate birthday) {
        logger.info("[ CustomerAPI#queryCustomersFromBirthday ]");

        // 誕生日開始日から顧客エンティティのリストを取得する
        List<Customer> customers = customerService.searchCustomersFromBirthday(birthday);

        // 顧客リスト（ボディ）とHTTPステータスOKを持つResponseEntityを返す
        return ResponseEntity.ok(customers);
    }
    
    // APIメソッド： 顧客を新規登録する
    @PostMapping(value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        logger.info("[ CustomerAPI#createCustomer ]");

        // 受け取った顧客エンティティを保存する
        customerService.registerCustomer(customer);

        // ボディが空で、HTTPステータスOKを持つResponseEntityを返す
        return ResponseEntity.ok(customer);
    }

    // APIメソッド： 顧客を置換する
    @PutMapping(value = "/{customerId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> replaceCustomer(
            @PathVariable("customerId") Integer customerId,
            @RequestBody Customer customer) {
        logger.info("[ CustomerAPI#replaceCustomer ]");

        // 受け取った顧客エンティティを置換する
        customer.setCustomerId(customerId);
        customerService.replaceCustomer(customer);

        // ボディが空で、HTTPステータスOKを持つResponseEntityを返す
        return ResponseEntity.ok().build();
    }

    // APIメソッド： 顧客を置換する
    @DeleteMapping(value = "/{customerId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable("customerId") Integer customerId) {
        logger.info("[ CustomerAPI#deleteCustomer ]");

        // 受け取った顧客IDを持つエンティティを削除する
        customerService.deleteCustomer(customerId);

        // ボディが空で、HTTPステータスOKを持つResponseEntityを返す
        return ResponseEntity.ok().build();
    }
}