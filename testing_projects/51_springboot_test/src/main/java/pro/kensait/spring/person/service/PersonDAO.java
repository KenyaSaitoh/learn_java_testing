package pro.kensait.spring.person.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class PersonDAO {
    // 主キー検索
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
        int count = 0;
        for (int id : personMap.keySet()) {
            if (id == personId) {
                personMap.remove(id);
                count = 1;
            }
        }
        dumpPersonMap();
        return count;
    }

    // 一件更新
    public int update(Person person) {
        int count = 0;
        for (int id : personMap.keySet()) {
            if (id == person.getPersonId()) {
                personMap.put(id, person);
                count = 1;
            }
        }
        dumpPersonMap();
        return count;
    }

    // 一件更新
    public int updateAge(int personId, int age) {
        int count = 0;
        for (int id : personMap.keySet()) {
            if (id == personId) {
                Person person = personMap.get(id);
                person.setAge(age);
                personMap.put(id, person);
                count = 1;
            }
        }
        dumpPersonMap();
        return count;
    }

    // 簡易データベース
    private static Map<Integer, Person> personMap = new ConcurrentHashMap<>();
    static {
        // Alice
        Person person1 = new Person(1, "Alice", 25, "female");
        personMap.put(1, person1);
        // Bob
        Person person2 = new Person(2, "Bob", 35, "male");
        personMap.put(2, person2);
        // Carol
        Person person3 = new Person(3, "Carol", 30, "female");
        personMap.put(3, person3);
    }

    private static void dumpPersonMap() {
        for (Person person : personMap.values()) {
            System.out.println(person);
        }
    }
}