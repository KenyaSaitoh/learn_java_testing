以下の「打鍵指示書」に従って負荷テストを行います。
Gatlingのコードを、概念的なJavaコードとして生成してください。
1つのシナリオは、「打鍵指示書」の最初から最後までとしてください。
個々のユーザー操作には、1秒間の休止時間を入れてください。
最初は1ユーザから始め、100秒かけて最大5ユーザーまで増加させてください。
起動してから120秒経過したら、シナリオ全体を終了してください。
生成するコードは、gatlingパッケージ配下でBookStoreSimulation3クラスという名前にしてください。

［制約条件］

* リンクはID属性を指定してクリックしてください。
* 要素とは、HTMLタグを表していますので、IDではありません。
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
|24|クリック|fixButton|||
|25|検証||title()|BookOrderPage|
|26|クリック|bankTransfer|||
|27|クリック|orderButton1|||
|28|クリック||ポップアップウィンドウ|キャンセル|
|29|クリック|orderButton1|||
|30|クリック||ポップアップウィンドウ|OK|
|31|クリック|orderButton1|||
|32|検証||title()|OrderSuccessPage|
|33|クリック|logoutButton|||
|34|検証||title()|FinishPage|

