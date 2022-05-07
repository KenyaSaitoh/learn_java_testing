package pro.kensait.java.app.person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PersonService {
    // 主キー検索
    public Person find(Integer personId) {
        for (Person person : personList) {
            if (person.getPersonId().equals(personId)) {
                return person;
            }
        }
        return null;
    }

    // 全件検索
    public List<Person> findAll() {
        Collections.sort(personList, new PersonPersonIdComparator());
        return personList;
    }

    // 全件検索（年齢ソート）
    public List<Person> findAllSortByAge(boolean isAsc) {
        List<Person> pList = new ArrayList<Person>(personList);
        if (isAsc) {
            Collections.sort(pList, new PersonAgeAscComparator());
        } else {
            Collections.sort(pList, new PersonAgeDescComparator());
        }
        return pList;
    }

    // 条件検索（指定された年齢以上）
    public List<Person> findPersonByLowerAge(Integer lowerAge) {
        List<Person> pList = new ArrayList<Person>();
        for (Person person : personList) {
            if (lowerAge <= person.getAge()) {
                pList.add(person);
            }
        }
        return pList;
    }

    private static List<Person> personList = new CopyOnWriteArrayList<Person>();

    // 簡易データベース
    static {
        // Alice
        Person person1 = new Person(1, "Alice", 25, "female");
        personList.add(person1);
        // Bob
        Person person2 = new Person(2, "Bob", 35, "male");
        personList.add(person2);
        // Carol
        Person person3 = new Person(3, "Carol", 30, "female");
        personList.add(person3);
        // Dave
        Person person4 = new Person(4, "Dave", 42, "male");
        personList.add(person4);
        // Ellen
        Person person5 = new Person(5, "Ellen", 28, "male");
        personList.add(person5);
    }

    static class PersonPersonIdComparator implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            if (p1.getPersonId() < p2.getPersonId())
                return -1;
            if (p1.getPersonId() > p2.getPersonId())
                return 1;
            return 0;
        }
    }

    static class PersonAgeAscComparator implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            if (p1.getAge() < p2.getAge())
                return 1;
            if (p1.getAge() > p2.getAge())
                return -1;
            return 0;
        }
    }

    static class PersonAgeDescComparator implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            if (p1.getAge() < p2.getAge())
                return -1;
            if (p1.getAge() > p2.getAge())
                return 1;
            return 0;
        }
    }
}