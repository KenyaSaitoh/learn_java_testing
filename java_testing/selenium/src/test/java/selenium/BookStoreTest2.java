package selenium;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Configuration;

class BookStoreTest2 {

    @Test
    void testCustomerRegistrationAndBookSelection() {
        Configuration.baseUrl = "http://localhost:8080";

        // 1. オープン
        open("/");

        // 2. クリック: newCustomerLink
        $("#newCustomerLink").click();

        // 3. ページのタイトル検証: CustomerInputPage
        assertEquals("CustomerInputPage", title());

        // 4. 入力: customerName
        $("#customerName").setValue("Frank");

        // 5. 入力: email
        $("#email").setValue("frank@gmail.com");

        // 6. 入力: password
        $("#password").setValue("password");

        // 7. 入力: birthday
        $("#birthday").setValue("2000/1/1");

        // 8. 入力: address
        $("#address").setValue("東京都新宿区1-1-1");

        // 9. クリック: registerButton
        $("#registerButton").click();

        // 10. ページのタイトル検証: CustomerOutputPage
        assertEquals("CustomerOutputPage", title());

        // 11. クリック: toSelectLink
        $("#toSelectLink").click();

        // 12. ページのタイトル検証: BookSelectPage
        assertEquals("BookSelectPage", title());

        // 13. テーブルの1行目の1列目の検証
        String firstBookTitle = $$("#bookstore-table tbody tr").first().$$("td").first().text();
        assertEquals("Java SEディープダイブ", firstBookTitle);

        // 14. テーブルの行数検証
        int rowCount = $$("#bookstore-table tbody tr").size();
        assertEquals(34, rowCount);

        // 15. クリック: button-2
        $("#button-2").click();

        // 16. クリック: toSelectLink
        $("#toSelectLink").click();

        // 17. クリック: button-5
        $("#button-5").click();

        // 18. クリック: toSelectLink
        $("#toSelectLink").click();

        // 19. クリック: button-2
        $("#button-2").click();

        // 20. クリック: fixButton
        $("#fixButton").click();

        // 21. ページのタイトル検証: BookOrderPage
        assertEquals("BookOrderPage", title());

        // 22. クリック: bankTransfer
        $("#bankTransfer").click();

        // 23. クリック: orderButton
        $("#orderButton1").click();

        // 24. ページのタイトル検証: OrderSuccessPage
        assertEquals("OrderSuccessPage", title());

        // 25. クリック: logoutButton
        $("#logoutButton").click();

        // 26. ページのタイトル検証: FinishPage
        assertEquals("FinishPage", title());
    }
}
