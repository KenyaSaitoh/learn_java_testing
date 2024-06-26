package pro.kensait.junit5.assertion;

import java.util.List;
import java.util.Objects;

public class Person2 {
    // フィールド
    private String name; // 名前
    private int age; // 年齢
    private List<String> hobbies;

    // コンストラクタ
    public Person2() {
    }

    public Person2(String name, int age, List<String> hobbies) {
        this.name = name;
        this.age = age;
        this.hobbies = hobbies;
    }

    // アクセサメソッド
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "Person2 [name=" + name + ", age=" + age + ", hobbies=" + hobbies + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, hobbies, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person2 other = (Person2) obj;
        return age == other.age && Objects.equals(hobbies, other.hobbies)
                && Objects.equals(name, other.name);
    }
}