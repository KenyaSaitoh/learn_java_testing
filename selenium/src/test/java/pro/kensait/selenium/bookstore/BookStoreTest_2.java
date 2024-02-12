package pro.kensait.selenium.bookstore;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

class BookStoreTest_2 {

    @BeforeAll
    static void setup() {
        // 現在日付と時刻をyyyyMMddHHmmssのフォーマットで取得する
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String dateTime = now.format(formatter);

        // スクリーンショットの保存先ディレクトリを設定する
        Configuration.reportsFolder = "build/reports/screenshots/BookStoreTest_2/" + dateTime;
    }

    @Test
    void test_BookStoreOperations() {
        Configuration.baseUrl = "http://localhost:8080";

        // 1. オープン
        open("http://localhost:8080");

        // 2. ページのタイトル検証: BookSelectPage
        assertEquals("TopPage", title());
        Selenide.screenshot("1-TopPage");

        // 3. 入力: email
        $("#email").setValue("alice@gmail.com");

        // 4. 入力: password
        $("#password").setValue("password");

        // 5. クリック: loginButton
        $("#loginButton").click();

        // 6. リダイレクト: /toSelect
        Selenide.sleep(1000); // 1秒待機
        open("/toSelect");

        // 7. ページのタイトル検証: BookSelectPage
        assertEquals("BookSelectPage", title());
        Selenide.screenshot("7-BookSelectPage");

        // 8. テーブルの1行目の1列目の検証
        String bookTitle = $$("#bookstore-table tbody tr").first().$$("td").first().text();
        assertEquals("Java SEディープダイブ", bookTitle);

        // 9. テーブルの行数検証
        int rowCount = $$("#bookstore-table tbody tr").size();
        assertEquals(34, rowCount);

        // 10. クリック: button-3
        $("#button-3").click();

        // 11. ページのタイトル検証: CartViewPage
        assertEquals("CartViewPage", title());
        Selenide.screenshot("11-CartViewPage");

        // 12. クリック: toSelectLink
        $("#toSelectLink").click();

        // 13. ページのタイトル検証: BookSelectPage
        assertEquals("BookSelectPage", title());
        Selenide.screenshot("13-BookSelectPage");

        // 14. クリック: button-5
        $("#button-5").click();

        // 15. ページのタイトル検証: CartViewPage
        assertEquals("CartViewPage", title());
        Selenide.screenshot("15-CartViewPage");

        // 16. クリック: toSelectLink
        $("#toSelectLink").click();

        // 17. ページのタイトル検証: BookSelectPage
        assertEquals("BookSelectPage", title());
        Selenide.screenshot("17-BookSelectPage");

        // 18. クリック: toSearchLink
        $("#toSearchLink").click();

        // 19. ページのタイトル検証: BookSearchPage
        assertEquals("BookSearchPage", title());
        Selenide.screenshot("19-BookSearchPage");

        // 20. 選択: category
        $("#category").selectOptionByValue("2");

        // 21. 入力: keyword
        $("#keyword").setValue("Cloud");

        // 22. クリック: search1Button
        $("#search1Button").click();

        // 23. ページのタイトル検証: BookSelectPage
        assertEquals("BookSelectPage", title());
        Selenide.screenshot("23-BookSelectPage");

        // 24. クリック: button-11
        $("#button-11").click();

        // 25. ページのタイトル検証: CartViewPage
        assertEquals("CartViewPage", title());
        Selenide.screenshot("27-CartViewPage");

        // 26. クリック: check-3
        $("#check-3").click();

        // 27. クリック: removeButton
        $("#removeButton").click();

        // 28. ページのタイトル検証: CartViewPage
        assertEquals("CartViewPage", title());
        Selenide.screenshot("28-CartViewPage");

        // 29. クリック: fixButton
        $("#fixButton").click();

        // 30. ページのタイトル検証: BookOrderPage
        assertEquals("BookOrderPage", title());
        Selenide.screenshot("30-BookOrderPage");

        // 31. クリック: bankTransfer
        $("#bankTransfer").click();

        // 32. クリック: orderButton
        $("#orderButton1").click();

        // 33. ポップアップウィンドウ キャンセル操作
        Selenide.dismiss();

        // 34. クリック: orderButton
        $("#orderButton1").click();

        // 35. ポップアップウィンドウ OK操作
        Selenide.confirm();

        // 36. ページのタイトル検証: OrderSuccessPage
        assertEquals("OrderSuccessPage", title());
        Selenide.screenshot("36-OrderSuccessPage");

        // 37. クリック: logoutButton
        $("#logoutButton").click();

        // 38. ページのタイトル検証: FinishPage
        assertEquals("FinishPage", title());
        Selenide.screenshot("38-FinishPage");
    }
}