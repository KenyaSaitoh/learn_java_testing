以下の「負荷テストシナリオ」に従って負荷テストを行います。
Gatlingのシミュレーションクラスを、概念的なJavaコードとして生成してください。

［制約条件］

* クラス名は、`BookStoreSimulation`とします。
* シミュレーションクラスは、`io.gatling.javaapi.core.Simulation`を継承してください。
* ベースURLは、`localhost:8080`に設定してください。
* シナリオは、`ScenarioBuilder`を使って生成してください。
* setUpには、イニシャライザーを使ってください。
* Titleの検証は、`css("title")`と指定することでTitleを取得してください。
* "Save CSRF"では、`#csrfToken`というIDのvalue属性から、CSRFトークンを取得してください。
* シナリオは、`pace()`メソッドによって、シナリオ番号1～12の間を、30秒間のペースに調整してください。
* シナリオは、`forever()`メソッドによって、無限に繰り返してください。
* 個々のユーザー操作には、2秒間の休止時間を入れてください。
* 最初は1ユーザから始め、100秒かけて最大5ユーザーまで増加させてください。
* 起動してから200秒経過したら、シナリオ全体を終了してください。

［負荷テストシナリオ］

|Num|Step|Action|Endpoint|Parameters|Checks|
|:--|:--|:--|:--|:--|:--|
|1|Open|GET|/||Status: 200, Title: "TopPage", Save CSRF|
|2|Login|POST|/processLogin|email: "alice@gmail.com", password: "password", _csrf: ${csrfToken}|Status: 200, Title: "BookSelectPage", Save CSRF|
|3|Add Book 1|POST|/addBook|bookId: "2", _csrf: ${csrfToken}|Status: 200, Title: "CartViewPage", Save CSRF|
|4|To Select 2|GET|/toSelect||Status: 200, Title: "BookSelectPage", Save CSRF|
|5|Add Book 2|POST|/addBook|bookId: "5", _csrf: ${csrfToken}|Status: 200, Title: "CartViewPage", Save CSRF|
|6|To Select 3|GET|/toSelect||Status: 200, Title: "BookSelectPage"|
|7|To Search|GET|/toSearch||Status: 200, Title: "BookSearchPage"|
|8|Search|GET|/search|categoryId: "2", keyword: "Cloud"|Status: 200, Title: "BookSelectPage", Save CSRF|
|9|Add Book 3|POST|/addBook|bookId: "14", _csrf: ${csrfToken}|Status: 200, Title: "CartViewPage", Save CSRF|
|10|Fix|POST|/fix|_csrf: ${csrfToken}|Status: 200, Title: "BookOrderPage", Save CSRF|
|11|Order|POST|/order1|settlementType: "1", _csrf: ${csrfToken}|Status: 200, Title: "OrderSuccessPage", Save CSRF|
|12|Logout|POST|/processLogout|_csrf: ${csrfToken}|Status: 200, Title: "FinishPage"|
