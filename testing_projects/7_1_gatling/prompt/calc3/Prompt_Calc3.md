以下の「負荷テストシナリオ」に従って負荷テストを行います。
Gatlingのシミュレーションクラスを、概念的なJavaコードとして生成してください。

［制約条件］

* クラス名は、`CalcSimulation`とします。
* シミュレーションクラスは、`io.gatling.javaapi.core.Simulation`を継承してください。
* ベースURLは、`localhost:8080`に設定してください。
* シナリオは、`ScenarioBuilder`を使って生成してください。
* setUpには、イニシャライザーを使ってください。
* Titleの検証は、`css("title")`と指定することでTitleを取得してください。
* シナリオは、シナリオ番号2～3の間を、5秒間のペースで、無限に繰り返してください。
* 個々のユーザー操作には、ランダムで1～4秒間の休止時間を入れてください。
* 最初は1ユーザから始め、30秒かけて最大3ユーザーまで増加させてください。
* 起動してから50秒経過したら、シナリオ全体を終了してください。

［負荷テストシナリオ］

|Num|Step|Action|Endpoint|Parameters|Checks|
|:--|:--|:--|:--|:--|:--|
|1|Open|GET|/||Status: 200, Title: "CalcInputPage"|
|2|Add|POST|/add|param1: "30", param2: "10"|Status: 200, Title: "CalcOutputPage"|
|3|Subtract|POST|/subtract|param1: "30", param2: "10"|Status: 200, Title: "CalcInputPage"|