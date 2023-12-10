package selenium;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

class BookStoreTest3 {

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

        // 8. クリック: toSearchLink
        $("#toSearchLink").click();

        // 9. ページのタイトル検証: BookSearchPage
        assertEquals("BookSearchPage", title());

        // 10. 選択: category
        $("#category").selectOptionByValue("1");

        // 11. クリック: search1Button
        $("#search1Button").click();

        // 12. ページのタイトル検証: BookSelectPage
        assertEquals("BookSelectPage", title());

        // 13. クリック: toSearchLink
        $("#toSearchLink").click();

        // 14. ページのタイトル検証: BookSearchPage
        assertEquals("BookSearchPage", title());

        // 15. 入力: keyword
        $("#keyword").setValue("SQL");

        // 16. クリック: search1Button
        $("#search1Button").click();

        // 17. ページのタイトル検証: BookSelectPage
        assertEquals("BookSelectPage", title());

        // 18. クリック: button-17
        $("#button-17").click();

        // 19. クリック: toSelectLink
        $("#toSelectLink").click();

        // 20. クリック: button-18
        $("#button-18").click();

        // 21. クリック: toSelectLink
        $("#toSelectLink").click();

        // 22. クリック: button-19
        $("#button-19").click();

        // 23. クリック: check-18
        $("#check-18").click();

        // 24. クリック: removeButton
        $("#removeButton").click();

        // 25. クリック: fixButton
        $("#fixButton").click();

        // 26. ページのタイトル検証: BookOrderPage
        assertEquals("BookOrderPage", title());

        // 27. クリック: bankTransfer
        $("#bankTransfer").click();

        // 28. クリック: orderButton
        $("#orderButton1").click();

        // 29. ページのタイトル検証: OrderSuccessPage
        assertEquals("OrderSuccessPage", title());

        // 30. クリック: logoutButton
        $("#logoutButton").click();

        // 31. ページのタイトル検証: FinishPage
        assertEquals("FinishPage", title());
    }
}