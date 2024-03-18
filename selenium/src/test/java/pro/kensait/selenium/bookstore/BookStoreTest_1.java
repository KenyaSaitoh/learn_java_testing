package pro.kensait.selenium.bookstore;

import static com.codeborne.selenide.Selenide.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

class BookStoreTest_1 {

    // テストクラス全体の前処理
    @BeforeAll
    static void initAll() {
        // WebブラウザをChromeに設定する
        Configuration.browser = "chrome";
        // ベースURLを設定する
        Configuration.baseUrl = "http://localhost:8080";
    }

    @Test
    void test_BookStoreOperations() {
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

        // 6. クリック: button-3
        $("#button-3").click();

        // 7. クリック: toSelectLink
        $("#toSelectLink").click();

        // 8. クリック: button-5
        $("#button-5").click();

        // 9. クリック: toSelectLink
        $("#toSelectLink").click();

        // 10. クリック: toSearchLink
        $("#toSearchLink").click();

        // 11. 選択: category
        // $("#category").selectOptionByValue("2");
        $("#category").selectOption("SpringBoot");

        // 12. 入力: keyword
        $("#keyword").setValue("Cloud");

        // 13. クリック: search1Button
        $("#search1Button").click();

        // 14. クリック: button-11
        $("#button-11").click();

        // 15. クリック: check-3
        $("#check-3").click();

        // 16. クリック: removeButton
        $("#removeButton").click();

        // 17. クリック: fixButton
        $("#fixButton").click();

        // 18. クリック: bankTransfer
        $("#bankTransfer").click();

        // 19. クリック: orderButton
        $("#orderButton1").click();

        // 20. ポップアップウィンドウ キャンセル操作
        Selenide.dismiss();

        // 21. クリック: orderButton
        $("#orderButton1").click();

        // 22. ポップアップウィンドウ OK操作
        Selenide.confirm();

        // 23. クリック: logoutButton
        $("#logoutButton").click();
    }
}