package pro.kensait.junit5.assertion;

import java.time.LocalDateTime;
import java.util.Objects;

public class Person2 {
    // フィールド
    private String name;
    private int age;
    private LocalDateTime lastUpdateTime; // 最終更新時間

    // コンストラクタ
    public Person2() {
    }

    public Person2(String name, int age, LocalDateTime lastUpdateTime) {
        this.name = name;
        this.age = age;
        this.lastUpdateTime = lastUpdateTime;
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

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", lastUpdateTime=" + lastUpdateTime + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
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
        return age == other.age && Objects.equals(name, other.name);
    }
}