package pro.kensait.spring.person.rest.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pro.kensait.spring.person.rest.service.Person;
import pro.kensait.spring.person.rest.service.PersonService;

@RestController
@RequestMapping("/persons")
@CrossOrigin
public class PersonApi {
    private static final Logger logger = LoggerFactory.getLogger(PersonApi.class);

    // インジェクションポイント
    @Autowired
    private PersonService personService;

    // APIメソッド：主キー検索によるPerson取得
    @GetMapping(path = "/{personId}")
    public ResponseEntity<Person> get(
            @PathVariable("personId") Integer personId) {
        logger.info("[ PersonApi#get ]");

        // ビジネスロジックを呼び出し、Personインスタンスを取得する
        Person result = personService.getPerson(personId);

        // ステータスが200でボディにPersonを保持するResponseEntityを生成し、返す
        return ResponseEntity.ok().body(result);
    }

    // APIメソッド：全Personリスト取得
    @GetMapping
    public ResponseEntity<List<Person>> getAll() {
        logger.info("[ PersonApi#getAll ]");

        // ビジネスロジックを呼び出し、Personインスタンスのリストを取得する
        List<Person> resultList = personService.getPersonsAll();

        // ステータスが200でボディにPersonリストを保持するResponseEntityを生成し、返す
        return ResponseEntity.ok().body(resultList);
    }

    // APIメソッド：条件検索によるPersonリスト取得
    @GetMapping(path = "/query_by_age")
    public ResponseEntity<List<Person>> queryByLowerAge(
            @RequestParam("lowerAge") Integer lowerAge) {
        logger.info("[ PersonApi#queryByLowerAge ]");

        // ビジネスロジックを呼び出し、Personインスタンスのリストを取得する
        List<Person> resultList = personService.getPersonsByLowerAge(lowerAge);

        // ステータスが200でボディにPersonリストを保持するResponseEntityを生成し、返す
        return ResponseEntity.ok().body(resultList);
    }

    // APIメソッド：Personの新規作成
    @PostMapping
    public ResponseEntity<Person> create(
            @RequestBody @Validated Person person,
            BindingResult errors) {
        logger.info("[ PersonApi#create ]");

        // 入力値検証の結果を調べ、エラーの場合はステータス400で応答する
        if (errors.hasErrors()) {
            return new ResponseEntity<Person>(HttpStatus.BAD_REQUEST);
        }

        // ビジネスロジックを呼び出し、Personを新規作成する
        // このとき、DBで新規採取されたIDを含むPersonインスタンスを取得する
        Person result = personService.createPerson(person);

        // ステータスが201でボディにPersonを保持するResponseEntityを生成し、返す
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // APIメソッド：Personの置換（更新または新規作成）
    @PutMapping(path = "/{personId}")
    public ResponseEntity<Person> replace(
            @RequestBody @Validated Person person,
            BindingResult errors) {
        logger.info("[ PersonApi#replace ]");

        // 入力値検証の結果を調べ、エラーの場合はステータス400で応答する
        if (errors.hasErrors()) {
            return new ResponseEntity<Person>(HttpStatus.BAD_REQUEST);
        }

        // ビジネスロジックを呼び出し、Personを置換する
        personService.replacePerson(person);

        // ステータスが200でボディにPersonを保持するResponseEntityを生成し、返す
        return ResponseEntity.ok().body(person);
    }

    // APIメソッド：Personの削除
    @DeleteMapping(path = "/{personId}")
    public ResponseEntity<Integer> delete(
            @PathVariable("personId") Integer personId) {
        logger.info("[ PersonApi#delete ]");

        // ビジネスロジックを呼び出し、Personを削除する
        int result = personService.removePerson(personId);

        // ステータスが200でボディにヒット件数を保持するResponseEntityを生成し、返す
        return ResponseEntity.ok().body(result);
    }
}