package pro.kensait.junit5.assertion;

import java.time.LocalDateTime;
import java.util.Objects;

public class Person1 {
    // フィールド
    private String name; // 名前
    private int age; // 年齢
    private LocalDateTime lastUpdateTime; // 最終更新時間

    // コンストラクタ
    public Person1() {
    }

    public Person1(String name, int age, LocalDateTime lastUpdateTime) {
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
        return "Person1 [name=" + name + ", age=" + age + ", lastUpdateTime=" + lastUpdateTime + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, lastUpdateTime, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person1 other = (Person1) obj;
        return age == other.age && Objects.equals(lastUpdateTime, other.lastUpdateTime)
                && Objects.equals(name, other.name);
    }
}