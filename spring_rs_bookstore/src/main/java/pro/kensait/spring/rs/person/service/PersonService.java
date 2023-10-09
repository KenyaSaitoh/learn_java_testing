package pro.kensait.spring.rs.person.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pro.kensait.spring.rs.person.repository.PersonRepository;

@Component
@Transactional
public class PersonService {
    private static final Logger logger = LoggerFactory.getLogger(
            PersonService.class);

    // インジェクションポイント
    @Autowired
    private PersonRepository personRepository;

    // 人物を取得する
    public Person getPerson(Integer personId) {
        logger.info("[ PersonService#getPerson ]");
         Optional<Person> result = personRepository.findById(personId);
         return result.get();
    }

    // 全人物を取得する
    public List<Person> getPersonsAll() {
        logger.info("[ PersonService#getPersonsAll ]");
        return personRepository.findAll();
    }

    // 人物を検索する（年齢下限をキーに）
    public List<Person> getPersonsByLowerAge(int lowerAge) {
        logger.info("[ PersonService#getPersonsByLowerAge ]");
        List<Person> personList = personRepository.selectByLowerAge(lowerAge);
        return personList;
    }

    // 人物を保存する
    public Person savePerson(Person person) {
        logger.info("[ PersonService#savePerson ]");
        return personRepository.save(person);
    }

    // 人物を削除する
    public int removePerson(Integer personId) {
        logger.info("[ PersonService#removePerson ]");
        Optional<Person> person = personRepository.findById(personId);
        if (person.get() == null) return 0;
        personRepository.deleteById(personId);
        return 1;
    }
}