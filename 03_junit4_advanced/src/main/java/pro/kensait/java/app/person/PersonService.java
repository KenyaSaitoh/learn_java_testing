package pro.kensait.java.app.person;

import java.util.List;

public class PersonService {

    private PersonMapper personMapper = new PersonMapper();

    public Person getPerson(int personId) {
        return personMapper.select(personId);
    }

    // 全Personリスト取得
    public List<Person> getPersonsAll() {
        return personMapper.selectAll();
    }

    // 全Personリスト取得（年齢でソート）
    public List<Person> getPersonsSortByAge(boolean isAsc) {
        return personMapper.selectSortByAge(isAsc);
    }

    // Personの作成
    public Person createPerson(Person person) {
        Person person2 = personMapper.insert(person);
        return person2;
    }

    // Personの更新
    public void replacePerson(Person person) {
        personMapper.update(person);
    }

    // Personの削除
    public void removePerson(int personId) {
        personMapper.delete(personId);
    }

    // Personの更新（年齢）
    public int updatePersonAge(Person person) {
        int result = personMapper.updateAge(person.getPersonId(),
                person.getAge());
        return result;
    }
}