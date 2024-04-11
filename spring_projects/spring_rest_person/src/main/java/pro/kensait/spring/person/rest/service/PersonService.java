package pro.kensait.spring.person.rest.service;

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

    // インジェクションポイント
    @Autowired
    private PersonRepos repos;

    // 人物を取得する
    public Person getPerson(int personId) {
        logger.info("[ PersonService#getPerson ]");
        Person person = repos.find(personId);
        return person;
    }

    // 全人物を取得する
    public List<Person> getPersonsAll() {
        logger.info("[ PersonService#getPersonsAll ]");
        List<Person> personList = repos.findAll();
        return personList;
    }

    // 人物を検索する（年齢下限をキーに）
    public List<Person> getPersonsByLowerAge(int lowerAge) {
        logger.info("[ PersonService#getPersonsByLowerAge ]");
        List<Person> personList = repos.findByLowerAge(lowerAge);
        return personList;
    }

    // 人物を置換（更新または追加）する
    public void replacePerson(Person person) {
        logger.info("[ PersonService#replacePerson ]");
        if (person.getPersonId() == null) {
            createPerson(person);
        } else {
            updatePerson(person);
        }
    }

    // 人物を追加する
    public Person createPerson(Person person) {
        logger.info("[ PersonService#createPerson ]");
        int maxPersonId = repos.getMaxPersonId();
        person.setPersonId(maxPersonId + 1);
        repos.save(person);
        return person;
    }

    // 人物を削除する
    public int removePerson(Integer personId) {
        logger.info("[ PersonService#removePerson ]");
        return repos.delete(personId);
    }

    // 人物を更新する
    public int updatePerson(Person person) {
        logger.info("[ PersonService#updatePerson ]");
        int result = repos.update(person);
        return result;
    }

    // 人物の年齢を更新する
    public int updatePersonAge(Person person) {
        logger.info("[ PersonService#updatePersonAge ]");
        int result = repos.updateAge(person.getPersonId(), person.getAge());
        return result;
    }
}