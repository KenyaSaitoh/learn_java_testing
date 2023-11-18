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

@Service
public class CustomerApiClient {
    private final String baseUrl = "http://localhost:8081/customers"; // APIのベースURL

    private static final Logger logger = LoggerFactory.getLogger(
            CustomerApiClient.class);

    @Autowired
    private RestTemplate restTemplate;

    // 顧客を主キーで取得
    public CustomerTO getCustomerById(Integer customerId) {
        logger.info("[ CustomerApiClient#getCustomerById ]");

        String url = baseUrl + "/" + customerId;
        ResponseEntity<CustomerTO> response = restTemplate.getForEntity(url, CustomerTO.class);
        return response.getBody();
    }

    // 顧客リソースをメールアドレスで検索
    public CustomerTO queryCustomerByEmail(String email) {
        logger.info("[ CustomerApiClient#queryCustomerByEmail ]");

        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/query_email")
                .queryParam("email", email)
                .toUriString();
        System.out.println(url + "########");
        ResponseEntity<CustomerTO> response =
                restTemplate.getForEntity(url, CustomerTO.class);
        System.out.println(response.getStatusCode() + "FFFFF");
        return response.getBody();
    }

    // 顧客リソースのリストを誕生日から検索
    public List<CustomerTO> queryCustomerFromBirthday(LocalDate birthday) {
        logger.info("[ CustomerApiClient#queryCustomerFromBirthday ]");

        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/query_birthday")
                .queryParam("birthday", birthday)
                .toUriString();
        ResponseEntity<List<CustomerTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CustomerTO>>() {}
            );
        return response.getBody();
    }

    // 顧客リソースの新規登録
    public CustomerTO createCustomer(CustomerTO customer) {
        logger.info("[ CustomerApiClient#createCustomer ]");

        ResponseEntity<CustomerTO> response =
                restTemplate.postForEntity(baseUrl + "/", customer, CustomerTO.class);
        return response.getBody();
    }

    // 顧客リソースの置換
    public CustomerTO replaceCustomer(Integer customerId, CustomerTO customer) {
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