以下の「打鍵指示書」に従って画面テストを行います。
Seleniumのコードを、Java（Selenide）で生成してください。

［制約条件］

* 生成するコードは、pro.kensait.selenium.bookstoreパッケージ配下のBookStoreTest_1クラスという名前で、
  JUnit5のテストクラスとして出力してください。
* URLは、最初にアクセスしたURLとポート番号をベースとして定義してください。
* リダイレクトは、1秒程度待ってから、テストプログラム内でGETしてください。
* リンクはID属性を指定してクリックしてください。

|番号|指示|ID|場所|VALUE|
|:--|--|--|--|--|
|1|オープン|||http://localhost:8080/|
|2|入力|email||alice@gmail.com|
|3|入力|password||password|
|4|クリック|loginButton||loginButton|
|5|リダイレクト|||/toSelect|
|6|クリック|button-3||3|
|7|クリック|toSelectLink|||
|8|クリック|button-5||5|
|9|クリック|toSelectLink|||
|10|クリック|toSearchLink|||
|11|選択|category||2|
|12|入力|keyword||Cloud|
|13|クリック|search1Button|||
|14|クリック|button-11||11|
|15|クリック|check-2|||
|16|クリック|removeButton|||
|17|クリック|fixButton|||
|18|クリック|bankTransfer|||
|19|クリック|orderButton1|||
|20|クリック||ポップアップウィンドウ|キャンセル|
|21|クリック|orderButton1|||
|22|クリック||ポップアップウィンドウ|OK|
|23|クリック|logoutButton|||

