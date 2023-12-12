package selenium;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

class BookStoreTest1 {

    @Test
    void testBookStoreOperations() {
        Configuration.baseUrl = "http://localhost:8080";

        // 1. オープン
        open("/");

        // 2. 入力: email
        $("#email").setValue("alice@gmail.com");

        // 3. 入力: password
        $("#password").setValue("password");

        // 4. クリック: loginButton
        $("#loginButton").click();

        // 5. リダイレクト: /toSelect
        Selenide.sleep(1000); // 1秒待機
        open("/toSelect");

        // 6. ページのタイトル検証: BookSelectPage
        assertEquals("BookSelectPage", title());

        // 7. テーブルの1行目の1列目の検証
        String bookTitle = $$("#bookstore-table tbody tr").first().$$("td").first().text();
        assertEquals("Java SEディープダイブ", bookTitle);

        // 8. クリック: button-2
        $("#button-2").click();

        // 9. クリック: toSelectLink
        $("#toSelectLink").click();

        // 10. クリック: button-5
        $("#button-5").click();

        // 11. クリック: toSelectLink
        $("#toSelectLink").click();

        // 12. クリック: button-2
        $("#button-2").click();

        // 13. クリック: fixButton
        $("#fixButton").click();

        // 14. ページのタイトル検証: BookOrderPage
        assertEquals("BookOrderPage", title());

        // 15. クリック: bankTransfer
        $("#bankTransfer").click();

        // 16. クリック: orderButton
        $("#orderButton1").click();

        // 17. ページのタイトル検証: OrderSuccessPage
        assertEquals("OrderSuccessPage", title());

        // 18. クリック: logoutButton
        $("#logoutButton").click();

        // 19. ページのタイトル検証: FinishPage
        assertEquals("FinishPage", title());
    }
}
