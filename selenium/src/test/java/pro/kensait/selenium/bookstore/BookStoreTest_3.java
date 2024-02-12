package pro.kensait.selenium.bookstore;

import static com.codeborne.selenide.Selenide.*;

import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Configuration;

class BookStoreTest_3 {

    @Test
    void test_BookStoreOperations() {
        Configuration.baseUrl = "http://localhost:8080";

        // 1. オープン
        open("/");

        // 2. クリック: newCustomerLink
        $("#newCustomerLink").click();

        // 3. 入力: customerName
        $("#customerName").setValue("Frank");

        // 4. 入力: email
        $("#email").setValue("frank@gmail.com");

        // 5. 入力: password
        $("#password").setValue("password");

        // 6. 入力: birthday
        $("#birthday").setValue("2000/1/1");

        // 7. 入力: address
        $("#address").setValue("東京都新宿区1-1-1");

        // 8. クリック: registerButton
        $("#registerButton").click();

        // 9. クリック: toSelectLink
        $("#toSelectLink").click();

        // 10. クリック: logoutButton
        $("#logoutButton").click();
    }
}
