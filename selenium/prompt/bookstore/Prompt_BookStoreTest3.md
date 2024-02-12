以下の「打鍵指示書」に従って画面テストを行います。
Seleniumのコードを、Java（Selenide）で生成してください。

［制約条件］

* 生成するコードは、pro.kensait.selenium.bookstoreパッケージ配下のBookStoreTest_3クラスという名前で、
  JUnit5のテストクラスとして出力してください。
* URLは、最初にアクセスしたURLとポート番号をベースとして定義してください。
* リダイレクトは、1秒程度待ってから、テストプログラム内でGETしてください。
* リンクはID属性を指定してクリックしてください。
*

［打鍵指示書］

|番号|指示|ID|場所|VALUE|
|:--|--|--|--|--|
|1|GET|||http://localhost:8080/|
|2|クリック|newCustomerLink|||
|3|入力|customerName||Frank|
|4|入力|email||frank@gmail.com|
|5|入力|password||password|
|6|入力|birthday||2000/1/1|
|7|入力|address||東京都新宿区1-1-1|
|8|クリック|registerButton|||
|9|クリック|toSelectLink|||
|10|クリック|logoutButton|||