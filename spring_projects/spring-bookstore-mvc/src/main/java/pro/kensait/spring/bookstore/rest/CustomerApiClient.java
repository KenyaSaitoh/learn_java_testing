package pro.kensait.spring.bookstore.rest;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@PropertySource("classpath:config.properties")
public class CustomerApiClient {
    @Value("${customer.api.base-url}")
    private String baseUrl; // 顧客APIのベースURL

    private static final Logger logger = LoggerFactory.getLogger(
            CustomerApiClient.class);

    @Autowired
    private RestTemplate restTemplate;

    // 顧客を主キーで取得
    public CustomerTO getById(Integer customerId) {
        logger.info("[ CustomerApiClient#getById ]");

        // GETメソッドでAPIを呼び出し、返されたデータをCustomerTOにバインドして受け取る
        String url = baseUrl + "/" + customerId;
        ResponseEntity<CustomerTO> response =
                restTemplate.getForEntity(url, CustomerTO.class);
        return response.getBody();
    }

    // 顧客リソースをメールアドレスで検索
    public CustomerTO queryByEmail(String email) {
        logger.info("[ CustomerApiClient#queryByEmail ]");

        // クエリパラメータを生成してURLに付加する
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/query_email")
                .queryParam("email", email)
                .toUriString();
        try {
            // GETメソッドでAPIを呼び出し、返されたデータをCustomerTOにバインドして受け取る
            ResponseEntity<CustomerTO> response =
                    restTemplate.getForEntity(url, CustomerTO.class);
            return response.getBody();

        // HTTPクライアントエラーが発生した場合の例外ハンドリング
        } catch (HttpClientErrorException hcex) {
            // 顧客が存在するかどうかを判定
            if (hcex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new CustomerNotFoundException(hcex);
            } else {
                throw hcex;
            }
        }
    }

    // 顧客リソースのリストを誕生日から検索
    public List<CustomerTO> queryFromBirthday(LocalDate birthday) {
        logger.info("[ CustomerApiClient#queryFromBirthday ]");

        // クエリパラメータを生成してURLに付加する
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/query_birthday")
                .queryParam("birthday", birthday)
                .toUriString();

        // GETメソッドでAPIを呼び出し、返されたデータをList<CustomerTO>にバインドして受け取る
        // → データがリストで返されるため、ParameterizedTypeReferenceで型を指定する
        // → getForEntity()ではなくexchange()を使う
        ResponseEntity<List<CustomerTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null, // リクエストは空（HTTPヘッダの追加やボディの指定はなし）
                new ParameterizedTypeReference<List<CustomerTO>>() {}
            );
        return response.getBody();
    }

    // 顧客リソースの新規登録
    public CustomerTO create(CustomerTO customer) {
        logger.info("[ CustomerApiClient#create ]");

        try {
            // POSTメソッドでAPIを呼び出し、返されたデータをCustomerTOにバインドして受け取る
            ResponseEntity<CustomerTO> response =
                    restTemplate.postForEntity(baseUrl + "/", customer, CustomerTO.class);
            return response.getBody();

        // HTTPクライアントエラーが発生した場合の例外ハンドリング
        } catch (HttpClientErrorException hcex) {
            // 顧客がすでに存在していたかどうかを判定
            if (hcex.getStatusCode() == HttpStatus.CONFLICT) {
                throw new CustomerExistsException(hcex);
            } else {
                throw hcex;
            }
        }
    }

    // 顧客リソースの置換
    public CustomerTO replace(Integer customerId, CustomerTO customer) {
        logger.info("[ CustomerApiClient#replace ]");

        // PUTメソッドでAPIを呼び出す
        String url = baseUrl + "/" + customerId;
        restTemplate.put(url, customer);
        return customer;
    }

    // 顧客リソースの削除
    public void delete(Integer customerId) {
        logger.info("[ CustomerApiClient#delete ]");

        // DELETEメソッドでAPIを呼び出す
        String url = baseUrl + "/" + customerId;
        restTemplate.delete(url);
    }
}