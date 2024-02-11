以下の「打鍵指示書」に従って画面テストを行います。
Seleniumのコードを、Java（Selenide）で生成してください。

［制約条件］

* 生成するコードは、pro.kensait.selenium.bookstoreパッケージ配下のBookStoreTest1クラスという名前で、
  JUnit5のテストクラスとして出力してください。
* 検証は、JUnit5のassert文を使ってください。
* ID属性は、"#"で表現してください。
* リンクはID属性を指定してクリックしてください。
* 要素（HTMLタグ）は、Selenideの"$$"を使ってください。
* URLは、最初にアクセスしたURLとポート番号をベースとして定義してください。
* リダイレクトは、1秒程度待ってから、テストプログラム内でGETしてください。

［打鍵指示書］

|#|指示|ID|場所|VALUE|
|:--|--|--|--|--|
|1|GET|||http://localhost:8080/|
|2|クリック|newCustomerLink|||
|3|検証||title()|CustomerInputPage|
|4|入力|customerName||Frank|
|5|入力|email||frank@gmail.com|
|6|入力|password||password|
|7|入力|birthday||2000/1/1|
|8|入力|address||東京都新宿区1-1-1|
|9|クリック|registerButton|||
|10|検証||title()|CustomerOutputPage|
|11|クリック|toSelectLink|||
|12|検証||title()|BookSelectPage|
|13|検証|bookstore-table|ボディ内の1行目の1列目|Java SEディープダイブ|
|14|検証|bookstore-table|ボディの行数|34|
|15|クリック|button-2|||
|16|クリック|toSelectLink|||
|17|クリック|button-5|||
|18|クリック|fixButton|||
|19|検証||title()|BookOrderPage|
|20|クリック|bankTransfer|||
|21|クリック|orderButton1|||
|22|クリック||ポップアップウィンドウ|キャンセル|
|23|クリック|orderButton1|||
|24|クリック||ポップアップウィンドウ|OK|
|25|検証||title()|OrderSuccessPage|
|26|クリック|logoutButton|||
|27|検証||title()|FinishPage|
