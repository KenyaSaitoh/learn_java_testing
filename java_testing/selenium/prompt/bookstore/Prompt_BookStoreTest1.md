以下の「打鍵指示書」に従って画面テストを行います。
Seleniumのコードを、Java（Selenide）で生成してください。
生成するコードは、seleniumパッケージ配下のBookStoreTest1クラスという名前で、JUnit5のテストクラスとして出力してください。

［制約条件］

* 検証は、JUnit5のassert文を使ってください。
* ID属性は、"#"で表現してください。
* リンクはID属性を指定してクリックしてください。
* 要素とは、HTMLタグを表していますので、IDではありません。
* 要素は、Selenideの"$$"を使ってください。
* URLは、最初にアクセスしたURLとポート番号をベースとして定義してください。
* リダイレクトは、1秒待ってから、テストプログラム内でGETでopenしてください。

|#|指示|ID|場所|VALUE|
|:--|--|--|--|--|
|1|オープン|||http://localhost:8080/|
|2|入力|email||alice@gmail.com|
|3|入力|password||password|
|4|クリック|loginButton||loginButton|
|5|リダイレクト|||/toSelect|
|6|検証||title()|BookSelectPage|
|7|検証|bookstore-table|ボディ内の1行目の1列目|Java SEディープダイブ|
|8|クリック|button-2||2|
|9|クリック|toSelectLink|||
|10|クリック|button-5||5|
|11|クリック|toSelectLink|||
|12|クリック|button-2||2|
|13|クリック|fixButton|||
|14|検証||title()|BookOrderPage|
|15|クリック|bankTransfer|||
|16|クリック|orderButton1|||
|17|検証||title()|OrderSuccessPage|
|18|クリック|logoutButton|||
|19|検証||title()|FinishPage|

