package pro.kensait.spring.bookstore.apiclient;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import pro.kensait.spring.bookstore.api.CustomerApi;
import pro.kensait.spring.bookstore.entity.Customer;

@Service
public class CustomerApiClient {
    private final String baseUrl = "http://localhost:8080/customers"; // APIのベースURL

    private static final Logger logger = LoggerFactory.getLogger(
            CustomerApi.class);

    @Autowired
    private RestTemplate restTemplate;

    // 顧客を主キーで取得
    public Customer getCustomerById(Integer customerId) {
        logger.info("[ CustomerApiClient#getCustomerById ]");

        String url = baseUrl + "/" + customerId;
        ResponseEntity<Customer> response = restTemplate.getForEntity(url, Customer.class);
        return response.getBody();
    }

    // 顧客リソースをメールアドレスで検索
    public Customer queryCustomerByEmail(String email) {
        logger.info("[ CustomerApiClient#queryCustomerByEmail ]");

        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/query_email")
                .queryParam("email", email)
                .toUriString();
        System.out.println(url + "########");
        ResponseEntity<Customer> response =
                restTemplate.getForEntity(url, Customer.class);
        System.out.println(response.getStatusCode() + "FFFFF");
        return response.getBody();
    }

    // 顧客リソースのリストを誕生日から検索
    public List<Customer> queryCustomerFromBirthday(LocalDate birthday) {
        logger.info("[ CustomerApiClient#queryCustomerFromBirthday ]");

        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/query_birthday")
                .queryParam("birthday", birthday)
                .toUriString();
        ResponseEntity<List<Customer>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Customer>>() {}
            );
        return response.getBody();
    }

    // 顧客リソースの新規登録
    public Customer createCustomer(Customer customer) {
        logger.info("[ CustomerApiClient#createCustomer ]");

        ResponseEntity<Customer> response =
                restTemplate.postForEntity(baseUrl + "/", customer, Customer.class);
        return response.getBody();
    }

    // 顧客リソースの置換
    public Customer replaceCustomer(Integer customerId, Customer customer) {
        logger.info("[ CustomerApiClient#replaceCustomer ]");

        String url = baseUrl + "/" + customerId;
        restTemplate.put(url, customer);
        return customer;
    }

    // 顧客リソースの削除
    public void deleteCustomer(Integer customerId) {
        logger.info("[ CustomerApiClient#deleteCustomer ]");
        
        String url = baseUrl + "/" + customerId;
        restTemplate.delete(url);
    }
}