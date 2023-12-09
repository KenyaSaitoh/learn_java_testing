以下の「打鍵指示書」に従って画面テストを行います。
Seleniumのコードを、Java（Selenide）で生成してください。
生成するコードは、seleniumパッケージ配下のBookStoreTestクラスという名前で、JUnit5のテストクラスとして出力してください。

［制約条件］

* 検証は、JUnit5のassert文を使ってください。
* ID属性は、"#"で表現してください。
* リンクはID属性を指定してクリックしてください。
* 要素とは、HTMLタグを表していますので、IDではありません。
* 要素は、Selenideの"$$"を使ってください。
* URLは、最初にアクセスしたURLとポート番号をベースとして定義してください。
* リダイレクトは、1秒待ってから、テストプログラム内でGETでopenしてください。

［打鍵指示書］
|#|指示|ID|場所|VALUE|
|:--|--|--|--|--|
|1|GET|||http://localhost:8080/|
|2|クリック|newCustomerLink|||
|3|検証||title()|CustomerInputPage|
|4|入力|customerName||Dave|
|5|入力|email||dave@gmail.com|
|6|入力|password||password|
|7|入力|birthday||2000/1/1|
|8|入力|address||東京都新宿区1-1-1|
|9|クリック|registerButton|||
|10|検証||title()|CustomerOutputPage|
|9|クリック|toSelectLink|||
|10|検証||title()|BookSelectPage|
|11|検証|bookstore-table|ボディ内の1行目の1列目|Java SEディープダイブ|
|12|クリック|button-2||2|
|13|クリック|toSelectLink|||
|14|クリック|button-5||5|
|15|クリック|toSelectLink|||
|16|クリック|button-2||2|
|17|クリック|fixButton|||
|18|検証||title()|BookOrderPage|
|19|クリック|bankTransfer|||
|20|クリック|orderButton|||
|21|検証||title()|OrderSuccessPage|
|22|クリック|logoutButton|||
|23|検証||title()|FinishPage|





