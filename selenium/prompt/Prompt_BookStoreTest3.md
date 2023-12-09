以下の「打鍵指示書」に従って画面テストを行います。
Seleniumのコードを、Java（Selenide）で生成してください。
生成するコードは、seleniumパッケージ配下のBookStoreTest3クラスという名前で、JUnit5のテストクラスとして出力してください。

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
|1|オープン|||http://localhost:8080/|
|2|入力|email||alice@gmail.com|
|3|入力|password||password|
|4|クリック|loginButton||loginButton|
|5|リダイレクト|||/toSelect|
|6|検証||title()|BookSelectPage|
|7|検証|bookstore-table|ボディ内の1行目の1列目|Java SEディープダイブ|
|8|クリック|toSearchLink|||
|9|検証||title()|BookSearchPage|
|10|選択|category||1|
|11|クリック|search1Button|||
|12|検証||title()|BookSelectPage|
|13|クリック|toSearchLink|||
|14|検証||title()|BookSearchPage|
|15|入力|keyword||SQL|
|16|検証||title()|BookSelectPage|
|17|クリック|button-17|||
|18|クリック|toSelectLink|||
|19|クリック|button-18|||
|20|クリック|toSelectLink|||
|21|クリック|button-19|||
|22|クリック|check-18|||
|23|クリック|removeButton|||
|23|クリック|fixButton|||
|24|検証||title()|BookOrderPage|
|25|クリック|bankTransfer|||
|26|クリック|orderButton|||
|27|検証||title()|OrderSuccessPage|
|28|クリック|logoutButton|||
|29|検証||title()|FinishPage|
