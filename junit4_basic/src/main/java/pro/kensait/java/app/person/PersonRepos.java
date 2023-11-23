package pro.kensait.java.app.person;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonRepos {

    // 一件検索
    public Person find(int id) {
        return personMap.get(id);
    }

    // 全件検索
    public List<Person> findAll() {
        return new ArrayList<Person>(personMap.values());
    }

    // 条件検索（指定された年齢以上）
    public List<Person> findByLowerAge(int lowerAge) {
        List<Person> pList = new ArrayList<Person>();
        for (Person person : personMap.values()) {
            if (lowerAge <= person.getAge()) {
                pList.add(person);
            }
        }
        return pList;
    }

    // ソート検索
    public List<Person> findAllSortByAge(boolean isAsc) {
        List<Person> pList = new ArrayList<Person>(personMap.values());
        if (isAsc) {
            pList.sort((p1, p2) -> p1.getAge().compareTo(p2.getAge()));
        } else {
            pList.sort((p1, p2) -> p2.getAge().compareTo(p1.getAge()));
        }
        return pList;
    }

    // 最大値取得
    public int getMaxPersonId() {
        int maxPersonId = 0;
        for (Person person : personMap.values()) {
            if (maxPersonId < person.getPersonId()) {
                maxPersonId = person.getPersonId();
            }
        }
        return maxPersonId;
    }

    // 挿入
    public int save(Person person) {
        int maxId = 0;
        for (int id : personMap.keySet()) {
            if (maxId <= id)
                maxId = id;
        }
        int nextId = maxId + 1;
        personMap.put(nextId, person);
        dumpPersonMap();
        return nextId;
    }

    // 一件削除
    public int delete(int personId) {
        for (int id : personMap.keySet()) {
            if (id == personId) {
                personMap.remove(id);
                return 1;
            }
        }
        dumpPersonMap();
        return 0;
    }

    // 一件更新
    public int update(Person person) {
        for (int id : personMap.keySet()) {
            if (id == person.getPersonId()) {
                personMap.put(id, person);
                return 1;
            }
        }
        dumpPersonMap();
        return 0;
    }

    // 一括更新
    public int updateAge(int personId, int age) {
        for (int id : personMap.keySet()) {
            if (id == personId) {
                Person person = personMap.get(id);
                person.setAge(age);
                personMap.put(id, person);
                return 1;
            }
        }
        dumpPersonMap();
        return 0;
    }

    // 簡易データベース
    private static Map<Integer, Person> personMap = new HashMap<>();
    static {
        LocalDateTime now = LocalDateTime.now();
        // Alice
        Person person1 = new Person(1, "Alice", 25, "female", now);
        personMap.put(1, person1);
        // Bob
        Person person2 = new Person(2, "Bob", 35, "male", now);
        personMap.put(2, person2);
        // Carol
        Person person3 = new Person(3, "Carol", 30, "female", now);
        personMap.put(3, person3);
        // Dave
        Person person4 = new Person(4, "Dave", 23, "male", now);
        personMap.put(4, person4);
        // Ellen
        Person person5 = new Person(5, "Ellen", 33, "male", now);
        personMap.put(5, person5);
    }



    private static void dumpPersonMap() {
        for (Person person : personMap.values()) {
            System.out.println(person);
        }
    }
}