以下の［制約条件］および［打鍵指示書］に従い、WebブラウザのUIテストを行います。
SelenideとJUnit5によるJavaのテストコードを、生成してください。

［制約条件］

* パッケージ名は`pro.kensait.selenium.bookstore`、クラス名は`BookStoreTest`とします。
* ベースURLとしてhttp://localhost:8080を設定してください。
* リダイレクトは、1秒程度待ってから、テストプログラム内でGETしてください。
* リンクはID属性を指定してクリックしてください。
* ID属性は、"#"で表現してください。
* 要素（HTMLタグ）は、Selenideの"$$"を使って取得してください。
* タイトルの検証には、JUnit5のアサーションAPIを使ってください。
* タイトル以外の検証には、Selenideの検証API（`shouldHave()`、`shouldBe()`など）を使ってください。
* タイトルの検証した直後に、ページのスクリーンショットを保存してください。
  保存名は、"番号-ページタイトル"とします。

［打鍵指示書］

|番号|指示|ID|場所|VALUE|
|:--|--|--|--|--|
|1|オープン||||
|2|検証||title()|TopPage|
|3|入力|email||alice@gmail.com|
|4|入力|password||password|
|5|クリック|loginButton||loginButton|
|6|リダイレクト|||/toSelect|
|7|検証||title()|BookSelectPage|
|8|検証|book-table|ボディ内の1行目の1列目|Java SEディープダイブ|
|9|検証|book-table|ボディの行数|34|
|10|クリック|button-3||3|
|11|検証||title()|CartViewPage|
|12|クリック|toSelectLink|||
|13|検証||title()|BookSelectPage|
|14|クリック|button-5||5|
|15|検証||title()|CartViewPage|
|16|クリック|toSelectLink|||
|17|検証||title()|BookSelectPage|
|18|クリック|toSearchLink|||
|19|検証||title()|BookSearchPage|
|20|選択|category||2|
|21|入力|keyword||Cloud|
|22|クリック|search1Button|||
|23|検証||title()|BookSelectPage|
|24|クリック|button-11||11|
|25|検証||title()|CartViewPage|
|26|クリック|check-3|||
|27|クリック|removeButton|||
|28|検証||title()|CartViewPage|
|29|クリック|fixButton|||
|30|検証||title()|BookOrderPage|
|31|クリック|bankTransfer|||
|32|クリック|orderButton1|||
|33|クリック||ポップアップウィンドウ|キャンセル|
|34|クリック|orderButton1|||
|35|クリック||ポップアップウィンドウ|OK|
|36|検証||title()|OrderSuccessPage|
|37|クリック|logoutButton|||
|38|検証||title()|FinishPage|
