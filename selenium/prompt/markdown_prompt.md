以下の画面遷移を行うためのSeleniumのコードを、Java（Selenide）で生成してください。
コードは、seleniumパッケージのBookStoreTestMainクラスという名前のメインクラスとして実装してください。
ページのタイトルの検証では、title()メソッドの戻り値をequalsメソッドで判定してください

|#|指示|URL|ID|場所|名前|値|
|--|--|--|--|--|--|--|
|1|GET|http://localhost:8080/|||||
|2|入力||email|||alice@gmail.com|
|3|入力||password|||password|
|4|POST|http://localhost:8080/processLogin|loginButton||||
|5|REDIRECT|http://localhost:8080/toSelect|||||
|6|検証|||title()||BookSelectPage|
|7|検証|||テーブルの2行目の1列目||Java SEディープダイブ|
|8|選択|||ボタン|bookId|2|


