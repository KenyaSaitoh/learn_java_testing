package pro.kensait.selenium.bookstore;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

class BookStoreTest_4 {

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
        String firstBookTitle = $$("#bookstore-table tbody tr").first().$$("td").first().text();
        assertEquals("Java SEディープダイブ", firstBookTitle);

        // 8. クリック: button-2
        $("#button-2").click();

        // 9. クリック: toSelectLink
        $("#toSelectLink").click();

        // 10. クリック: button-5
        $("#button-5").click();

        // 11. クリック: toSelectLink
        $("#toSelectLink").click();

        // 12. クリック: toSearchLink
        $("#toSearchLink").click();

        // 13. ページのタイトル検証: BookSearchPage
        assertEquals("BookSearchPage", title());

        // 14. 選択: category
        $("#category").selectOptionByValue("2");

        // 15. 入力: keyword
        $("#keyword").setValue("Cloud");

        // 16. クリック: search1Button
        $("#search1Button").click();

        // 17. ページのタイトル検証: BookSelectPage
        assertEquals("BookSelectPage", title());

        // 18. クリック: button-14
        $("#button-14").click();

        // 19. クリック: fixButton
        $("#fixButton").click();

        // 20. ページのタイトル検証: BookOrderPage
        assertEquals("BookOrderPage", title());

        // 21. クリック: bankTransfer
        $("#bankTransfer").click();

        // 22. クリック: orderButton
        $("#orderButton1").click();

        // 23. ポップアップウィンドウ キャンセル操作
        Selenide.dismiss();

        // 24. クリック: orderButton
        $("#orderButton1").click();

        // 25. ポップアップウィンドウ OK操作
        Selenide.confirm();

        // 26. ページのタイトル検証: OrderSuccessPage
        assertEquals("OrderSuccessPage", title());

        // 27. クリック: logoutButton
        $("#logoutButton").click();

        // 28. ページのタイトル検証: FinishPage
        assertEquals("FinishPage", title());
    }
}