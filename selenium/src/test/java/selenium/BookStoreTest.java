package selenium;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Configuration;

public class BookStoreTest {

    @Test
    public void testBookStoreOperations() {
        Configuration.baseUrl = "http://localhost:8080";

        // 1. GETリクエスト: http://localhost:8080/
        open("/");

        // 2. emailを入力
        $("#email").setValue("alice@gmail.com");

        // 3. passwordを入力
        $("#password").setValue("password");

        // 4. POSTリクエスト: /processLogin
        $("#loginButton").click();

        // 5. REDIRECT: /toSelect
        open("/toSelect");

        // 6. ページのタイトル検証
        assertEquals("BookSelectPage", title(), "ページのタイトルが期待と異なります。");

        // 7. テーブルの1行目の1列目の検証
        String actualText = $$("table tbody tr").first().$$("td").first().text();
        assertEquals("Java SEディープダイブ", actualText, "テーブルのテキストが期待値と一致しません。");

        // 8. /addBookへPOSTリクエスト: bookId=2
        $("#button-2").click();

        // 9. GETリクエスト: /toSelect
        open("/toSelect");

        // 10. /addBookへPOSTリクエスト: bookId=5
        $("#button-5").click();

        // 11. GETリクエスト: /toSelect
        open("/toSelect");

        // 12. /addBookへPOSTリクエスト: bookId=2
        $("#button-2").click();

        // 13. GETリクエスト: /toSelect
        open("/toSelect");
    }
}
