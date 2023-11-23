package pro.kensait.java.app.person;

import java.util.List;

public class PersonService {

    // インジェクションポイント
    private PersonRepos repos;

    // 人物を取得する
    public Person getPerson(int personId) {
        Person person = repos.find(personId);
        return person;
    }

    // 全人物を取得する
    public List<Person> getPersonsAll() {
        List<Person> personList = repos.findAll();
        return personList;
    }

    // 人物を検索する（年齢下限をキーに）
    public List<Person> getPersonsByLowerAge(int lowerAge) {
        List<Person> personList = repos.findByLowerAge(lowerAge);
        return personList;
    }

    // 人物を検索する（年齢下限をキーに）
    public List<Person> getPersonsAllSortByAge(boolean isAsc) {
        List<Person> personList = repos.findAllSortByAge(isAsc);
        return personList;
    }
    
    // 人物を置換（更新または追加）する
    public void replacePerson(Person person) {
        if (person.getPersonId() == null) {
            createPerson(person);
        } else {
            updatePerson(person);
        }
    }

    // 人物を追加する
    public Person createPerson(Person person) {
        int maxPersonId = repos.getMaxPersonId();
        person.setPersonId(maxPersonId + 1);
        repos.save(person);
        return person;
    }

    // 人物を削除する
    public int removePerson(Integer personId) {
        return repos.delete(personId);
    }

    // 人物を更新する
    public int updatePerson(Person person) {
        int result = repos.update(person);
        return result;
    }

    // 人物の年齢を更新する
    public int updatePersonAge(Person person) {
        int result = repos.updateAge(person.getPersonId(), person.getAge());
        return result;
    }
}