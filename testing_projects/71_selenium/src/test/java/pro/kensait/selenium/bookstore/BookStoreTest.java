package pro.kensait.selenium.bookstore;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Configuration;

public class BookStoreTest {

    // テストクラス全体の前処理
    @BeforeAll
    static void initAll() {
        // WebブラウザをChromeに設定する
        Configuration.browser = "chrome";
        // ベースURLを設定する
        Configuration.baseUrl = "http://localhost:8080";

        // 現在日付と時刻をyyyyMMddHHmmssのフォーマットで取得する
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String dateTime = now.format(formatter);

        // スクリーンショットの保存先ディレクトリを設定する
        Configuration.reportsFolder = "build/reports/screenshots/BookStoreTest/" + dateTime;
    }

    @Test
    void test_BookStoreOperations() {
        // 1. オープン
        open("");

        // 2. ページのタイトル検証: TopPage
        assertEquals("TopPage", title());
        screenshot("1-TopPage");

        // 3. 入力: email
        $("#email").setValue("alice@gmail.com");

        // 4. 入力: password
        $("#password").setValue("password");

        // 5. クリック: loginButton
        $("#loginButton").click();

        // 6. リダイレクト: /toSelect
        sleep(1000); // 1秒待機
        open("/toSelect");

        // 7. ページのタイトル検証: BookSelectPage
        assertEquals("BookSelectPage", title());
        screenshot("7-BookSelectPage");

        // 8. テーブルの1行目の1列目の検証
        //SelenideElement elem = $$("#book-table tbody tr").first().$$("td").first();
        $$("#book-table tbody tr").first().$$("td").first().shouldHave(text("Java SEディープダイブ"));

        // 9. テーブルの行数検証
        assertEquals(34, $$("#book-table tbody tr").size());

        // 10. クリック: button-3
        $("#button-3").click();

        // 11. ページのタイトル検証: CartViewPage
        assertEquals("CartViewPage", title());
        screenshot("11-CartViewPage");

        // 12. クリック: toSelectLink
        $("#toSelectLink").click();

        // 13. ページのタイトル検証: BookSelectPage
        assertEquals("BookSelectPage", title());
        screenshot("13-BookSelectPage");

        // 14. クリック: button-5
        $("#button-5").click();

        // 15. ページのタイトル検証: CartViewPage
        assertEquals("CartViewPage", title());
        screenshot("15-CartViewPage");

        // 16. クリック: toSelectLink
        $("#toSelectLink").click();

        // 17. ページのタイトル検証: BookSelectPage
        assertEquals("BookSelectPage", title());
        screenshot("17-BookSelectPage");

        // 18. クリック: toSearchLink
        $("#toSearchLink").click();

        // 19. ページのタイトル検証: BookSearchPage
        assertEquals("BookSearchPage", title());
        screenshot("19-BookSearchPage");

        // 20. 選択: category
        // $("#category").selectOptionByValue("2");
        $("#category").selectOption("SpringBoot");

        // 21. 入力: keyword
        $("#keyword").setValue("Cloud");

        // 22. クリック: search1Button
        $("#search1Button").click();

        // 23. ページのタイトル検証: BookSelectPage
        assertEquals("BookSelectPage", title());
        screenshot("23-BookSelectPage");

        // 24. クリック: button-11
        $("#button-11").click();

        // 25. ページのタイトル検証: CartViewPage
        assertEquals("CartViewPage", title());
        screenshot("27-CartViewPage");

        // 26. クリック: check-3
        $("#check-3").click();

        // 27. クリック: removeButton
        $("#removeButton").click();

        // 28. ページのタイトル検証: CartViewPage
        assertEquals("CartViewPage", title());
        screenshot("28-CartViewPage");

        // 29. クリック: fixButton
        $("#fixButton").click();

        // 30. ページのタイトル検証: BookOrderPage
        assertEquals("BookOrderPage", title());
        screenshot("30-BookOrderPage");

        // 機能確認：#orderButton1が表示されていることの検証
        $("#orderButton1").shouldBe(visible);

        // 機能確認：#orderButton3が存在しないことの検証
        $("#orderButton3").shouldNotBe(exist);

        // 機能確認：#orderButton1が属性名"formaction"、属性値"/order1"を持っていることの検証
        $("#orderButton1").shouldHave(attribute("formaction", "/order1"));

        // 機能確認：name属性settlementTypeが選択されていないことの検証
        $(byName("settlementType")).shouldNotBe(selected);

        // 31. クリック: name属性settlementTypeを"1"に
        $(byName("settlementType")).selectRadio("1");

        // 32. クリック: orderButton
        $("#orderButton1").click();

        // 33. ポップアップウィンドウ キャンセル操作
        dismiss();

        // 34. クリック: orderButton
        $("#orderButton1").click();

        // 35. ポップアップウィンドウ OK操作
        confirm();

        // 36. ページのタイトル検証: OrderSuccessPage
        assertEquals("OrderSuccessPage", title());
        screenshot("37-OrderSuccessPage");

        // 37. クリック: logoutButton
        $("#logoutButton").click();

        // 38. ページのタイトル検証: FinishPage
        assertEquals("FinishPage", title());
        screenshot("38-FinishPage");
    }
}