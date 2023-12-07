package selenium;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.Configuration;

public class BookStoreTestMain {

    public static void main(String[] args) {
        Configuration.baseUrl = "http://localhost:8080";
        Configuration.timeout = 10000; // 応答を待つ最大時間（ミリ秒）

        // 1. 指定のURLにアクセス
        open("/");

        // 2. メールアドレスを入力
        $("input[name='email']").setValue("alice@gmail.com");

        // 3. パスワードを入力
        $("input[name='password']").setValue("password");

        // 4. ログインボタンをクリック
        $("#loginButton").click(); // IDでの要素選択

        // 5. リダイレクト後のページに移動
        open("/toSelect");

        // 6. ページのタイトルを検証
        if ("BookSelectPage".equals(title())) {
            System.out.println("タイトルは正しいです。");
        } else {
            System.out.println("タイトルが異なります。");
        }

        // 7. テーブルの2行目の1列目が"Java SEディープダイブ"であることを検証
        $$("table tr").get(1).$$("td").first().shouldHave(text("Java SEディープダイブ"));

        // 8. bookIdが2の項目を選択
        $("button[name='bookId'][value='2']").click();
    }
}
