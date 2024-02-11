以下の「打鍵指示書」に従って画面テストを行います。
Seleniumのコードを、Java（Selenide）で生成してください。

［制約条件］

* 生成するコードは、pro.kensait.selenium.bookstoreパッケージ配下のBookStoreTest4クラスという名前で、
  JUnit5のテストクラスとして出力してください。
* 検証は、JUnit5のassert文を使ってください。
* ID属性は、"#"で表現してください。
* リンクはID属性を指定してクリックしてください。
* 要素（HTMLタグ）は、Selenideの"$$"を使ってください。
* URLは、最初にアクセスしたURLとポート番号をベースとして定義してください。
* リダイレクトは、1秒程度待ってから、テストプログラム内でGETでopenしてください。

［打鍵指示書］

|#|指示|ID|場所|VALUE|
|:--|--|--|--|--|
|1|オープン|||http://localhost:8080/|
|2|入力|email||alice@gmail.com|
|3|入力|password||password|
|4|クリック|loginButton||loginButton|
|5|リダイレクト|||/toSelect|
|6|検証||title()|BookSelectPage|
|7|検証|bookstore-table|ボディ内の1行目の1列目|Java SEディープダイブ|
|8|クリック|button-2|||
|9|クリック|toSelectLink|||
|10|クリック|button-5|||
|11|クリック|toSelectLink|||
|12|クリック|toSearchLink|||
|13|検証||title()|BookSearchPage|
|14|選択|category||2|
|15|入力|keyword||Cloud|
|16|クリック|search1Button|||
|17|検証||title()|BookSelectPage|
|18|クリック|button-14|||
|19|クリック|fixButton|||
|20|検証||title()|BookOrderPage|
|21|クリック|bankTransfer|||
|22|クリック|orderButton1|||
|23|クリック||ポップアップウィンドウ|キャンセル|
|24|クリック|orderButton1|||
|25|クリック||ポップアップウィンドウ|OK|
|26|検証||title()|OrderSuccessPage|
|27|クリック|logoutButton|||
|28|検証||title()|FinishPage|
