以下の「打鍵指示書」に従って画面テストを行います。
Seleniumのコードを、Java（Selenide）で生成してください。

［制約条件］

* 生成するコードは、seleniumパッケージ配下のCalc3Testクラスという名前で、JUnit5のテストクラスとして出力してください。
* 検証は、JUnit5のassert文を使ってください。
* ID属性は、"#"で表現してください。
* リンクはID属性を指定してクリックしてください。
* 要素（HTMLタグ）は、Selenideの"$$"を使ってください。
* URLは、最初にアクセスしたURLとポート番号をベースとして定義してください。
* リダイレクトは、1秒程度待ってから、テストプログラム内でGETしてください。

［打鍵指示書］

|#|指示|ID|場所|VALUE|
|:--|--|--|--|--|
|1|オープン|||http://localhost:8080/|
|2|入力|param1||30|
|3|入力|param2||10|
|4|クリック|addButton|||
|5|検証||title()|CalcOutputPage|
|6|検証|result||40|
|7|戻る||||
|8|入力|param1||500|
|9|入力|param2||500|
|10|クリック|subtractButton|||
|11|検証||title()|CalcInputPage|
|12|検証|globalErrors|配下の任意の場所にエラーが存在すること||


