package pro.kensait.spring.person.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Personに対するビジネスロジックを表すクラス
 */
@Service
public class PersonService {
    private static final Logger logger = LoggerFactory.getLogger(
            PersonService.class);

    // DAO（インジェクション）
    @Autowired
    private PersonDAO personDao;

    // コンストラクタ
    public PersonService(PersonDAO personDao) {
        this.personDao = personDao;
    }

    // サービスメソッド：人物を取得する
    public Person getPerson(int personId) {
        logger.info("[ PersonService#getPerson ]");
        Person person = personDao.find(personId);
        return person;
    }

    // サービスメソッド：全人物を取得する
    public List<Person> getPersonsAll() {
        logger.info("[ PersonService#getPersonsAll ]");
        List<Person> personList = personDao.findAll();
        return personList;
    }

    // サービスメソッド：人物を検索する（年齢下限をキーに）
    public List<Person> getPersonsByLowerAge(int lowerAge) {
        logger.info("[ PersonService#getPersonsByLowerAge ]");
        List<Person> personList = personDao.findByLowerAge(lowerAge);
        return personList;
    }

    // サービスメソッド：人物を追加する
    public Person createPerson(Person person) {
        logger.info("[ PersonService#createPerson ]");
        int maxPersonId = personDao.getMaxPersonId();
        person.setPersonId(maxPersonId + 1);
        personDao.save(person);
        return person;
    }

    // サービスメソッド：人物を削除する
    public int removePerson(Integer personId) {
        logger.info("[ PersonService#removePerson ]");
        return personDao.delete(personId);
    }

    // サービスメソッド：人物を更新する
    public int updatePerson(Person person) {
        logger.info("[ PersonService#updatePerson ]");
        int result = personDao.update(person);
        return result;
    }

    // サービスメソッド：人物の年齢を更新する
    public int updatePersonAge(Person person) {
        logger.info("[ PersonService#updatePersonAge ]");
        int result = personDao.updateAge(person.getPersonId(), person.getAge());
        return result;
    }
}