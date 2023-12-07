以下の「打鍵指示書」に従って画面テストを行います。
Seleniumのコードを、Java（Selenide）で生成してください。
生成するコードは、seleniumパッケージ配下のBookStoreTestクラスという名前で、JUnit5のテストクラスとして出力してください。
検証は、JUnit5のassert文を使ってください。

［打鍵指示書］

|#|指示|URL|要素|ID|NAME|場所|VALUE|
|:--|--|--|--|--|--|--|--|
|1|GET|http://localhost:8080/||||||
|2|入力|||email|||alice@gmail.com|
|3|入力|||password|||password|
|4|POST|/processLogin||loginButton||||
|5|REDIRECT|/toSelect||||||
|6|検証|||||title()|BookSelectPage|
|7|検証||table body|||1行目の1列目|Java SEディープダイブ|
|8|選択してPOST|||button-2|bookId||2|
|9|GET|/toSelect||||||
|10|選択してPOST|||button-5|bookId||5|
|11|GET|/toSelect||||||
|12|選択してPOST|||button-2|bookId||2|
|13|GET|/toSelect||||||





