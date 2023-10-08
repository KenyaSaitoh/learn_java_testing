package pro.kensait.spring.rs.person.api;

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

import pro.kensait.spring.rs.person.service.Person;
import pro.kensait.spring.rs.person.service.PersonService;

@RestController
@RequestMapping("/persons")
@CrossOrigin
public class PersonController {
    private static final Logger logger = LoggerFactory.getLogger(
            PersonController.class);

    // インジェクションポイント
    @Autowired
    private PersonService personService;

    // リソースの取得（主キー検索によるPerson取得）
    @GetMapping(path = "/{personId}")
    public ResponseEntity<Person> getPerson(
            @PathVariable("personId") Integer personId) {
        logger.info("[ PersonController#getPerson ]");

        // ビジネスロジックを呼び出し、Personインスタンスを取得する
        Person person = personService.getPerson(personId);

        // ボディにPersonインスタンス、ステータスに200を保持する
        // ResponseEntityを生成し、返す
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

    // リソースの取得（全Personリスト取得）
    @GetMapping
    public ResponseEntity<List<Person>> getPersonsAll() {
        logger.info("[ PersonController#getPersonsAll ]");

        // ビジネスロジックを呼び出し、Personインスタンスのリストを取得する
        List<Person> personList = personService.getPersonsAll();

        // ボディにPersonインスタンスのリスト、ステータスに200を保持する
        // ResponseEntityを生成し、返す
        return new ResponseEntity<List<Person>>(personList, HttpStatus.OK);
    }

    // リソースの取得（条件検索によるPersonリスト取得）
    @GetMapping(path = "/query_by_age")
    public ResponseEntity<List<Person>> getPersonsByLowerAge(
            @RequestParam("lowerAge") Integer lowerAge) {
        logger.info("[ PersonController#getPersonsByLowerAge ]");

        // ビジネスロジックを呼び出し、Personインスタンスのリストを取得する
        List<Person> personList = personService.getPersonsByLowerAge(lowerAge);

        // ボディにPersonインスタンスのリスト、ステータスに200を保持する
        // ResponseEntityを生成し、返す
        return new ResponseEntity<List<Person>>(personList, HttpStatus.OK);
    }

    // リソースの作成（Personの挿入）
    @PostMapping
    public ResponseEntity<Person> createPerson(
            @RequestBody @Validated Person person,
            BindingResult errors) {
        logger.info("[ PersonController#createPerson ]");

        // 入力値検証の結果を調べ、エラーの場合はステータス400で応答する
        if (errors.hasErrors()) {
            return new ResponseEntity<Person>(HttpStatus.BAD_REQUEST);
        }

        // ビジネスロジックを呼び出し、Personを挿入する
        // このとき、DBで新規採取されたIDを含むPersonインスタンスを取得する
        Person person2 = personService.savePerson(person);

        // ボディにPersonインスタンス、ステータスに200を保持する
        // ResponseEntityを生成し、返す
        return new ResponseEntity<Person>(person2, HttpStatus.OK);
    }

    // リソースの置換（Personの更新または挿入）
    @PutMapping(path = "/{personId}")
    public ResponseEntity<Person> replacePerson(
            @RequestBody @Validated Person person,
            BindingResult errors) {
        logger.info("[ PersonController#replacePerson ]");

        // 入力値検証の結果を調べ、エラーの場合はステータス400で応答する
        if (errors.hasErrors()) {
            return new ResponseEntity<Person>(HttpStatus.BAD_REQUEST);
        }

        // ビジネスロジックを呼び出し、Personを置換する
        personService.savePerson(person);

        // ステータスに200を保持するResponseEntityを生成し、返す
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

    // リソースの削除（Personの削除）
    @DeleteMapping(path = "/{personId}")
    public ResponseEntity<Integer> removePerson(
            @PathVariable("personId") Integer personId) {
        logger.info("[ PersonController#removePerson ]");

        // ビジネスロジックを呼び出し、Personを削除する
        int result = personService.removePerson(personId);

        // ボディにヒット件数、ステータスに200を保持する
        // ResponseEntityを生成し、返す
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }
}