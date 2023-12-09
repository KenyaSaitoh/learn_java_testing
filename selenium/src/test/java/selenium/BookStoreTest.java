package selenium;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Configuration;

public class BookStoreTest {

    @Test
    public void testCustomerRegistrationAndBookSelection() {
        Configuration.baseUrl = "http://localhost:8080";
        Configuration.timeout = 1000;

        // 1. GETリクエスト
        open("/");

        // 2. クリック: newCustomerLink
        $("#newCustomerLink").click();

        // 3. ページのタイトル検証: CustomerInputPage
        assertEquals("CustomerInputPage", title(), "ページのタイトルが期待と異なります。");

        // 4. 入力: customerName
        $("#customerName").setValue("Dave");

        // 5. 入力: email
        $("#email").setValue("dave@gmail.com");

        // 6. 入力: password
        $("#password").setValue("password");

        // 7. 入力: birthday
        $("#birthday").setValue("2000/1/1");

        // 8. 入力: address
        $("#address").setValue("東京都新宿区1-1-1");

        // 9. クリック: registerButton
        $("#registerButton").click();

        // 10. ページのタイトル検証: CustomerOutputPage
    //    assertEquals("CustomerOutputPage", title(), "ページのタイトルが期待と異なります。");

        // 11. クリック: toSelectLink
        $("#toSelectLink").click();

        // 12. ページのタイトル検証: BookSelectPage
        assertEquals("BookSelectPage", title(), "ページのタイトルが期待と異なります。");

        // 13. テーブルの1行目の1列目の検証
        String actualText = $$("#bookstore-table tbody tr").first().$$("td").first().text();
        assertEquals("Java SEディープダイブ", actualText, "テーブルのテキストが期待値と一致しません。");

        // 14. クリック: button-2
        $("#button-2").click();

        // 15. クリック: toSelectLink
        $("#toSelectLink").click();

        // 16. クリック: button-5
        $("#button-5").click();

        // 17. クリック: toSelectLink
        $("#toSelectLink").click();

        // 18. クリック: button-2
        $("#button-2").click();

        // 19. クリック: fixButton
        $("#fixButton").click();

        // 20. ページのタイトル検証: BookOrderPage
        assertEquals("BookOrderPage", title(), "ページのタイトルが期待と異なります。");

        // 21. クリック: bankTransfer
        $("#bankTransfer").click();

        // 22. クリック: orderButton
        $("#orderButton").click();

        // 23. ページのタイトル検証: OrderSuccessPage
        assertEquals("OrderSuccessPage", title(), "ページのタイトルが期待と異なります。");

        // 24. クリック: logoutButton
        $("#logoutButton").click();

        // 25. ページのタイトル検証: FinishPage
        assertEquals("FinishPage", title(), "ページのタイトルが期待と異なります。");
    }
}
