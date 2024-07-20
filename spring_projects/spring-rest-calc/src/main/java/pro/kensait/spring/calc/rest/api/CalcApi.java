package pro.kensait.spring.calc.rest.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * 計算機能のAPIを提供するクラス
 */
@RestController
@RequestMapping("/calc")
@CrossOrigin
public class CalcApi {

    // APIメソッド：足し算実行
    @GetMapping(path = "/add1")
    public ResponseEntity<Double> add1(@RequestParam Double param1,
            @RequestParam Double param2) {

        // 計算処理を実行する
        // ここではサービスに処理を委譲せず、直接実装
        double result = param1 + param2;

        System.out.println("[ CalcApi#add1 ] result => " + result);
        
        // ステータスが200でボディに計算結果を保持するResponseEntityを生成し、返す
        return ResponseEntity.ok().body(result);

        // 以下のようにすることも可
        // return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // APIメソッド：足し算実行
    @PostMapping(path = "/add2")
    public ResponseEntity<Double> add2(@RequestParam Double param1,
            @RequestParam Double param2) {

        // 計算処理を実行する
        double result = param1 + param2;

        // ステータスが200でボディに計算結果を保持するResponseEntityを生成し、返す
        return ResponseEntity.ok().body(result);
    }
}