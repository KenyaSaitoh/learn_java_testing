package pro.kensait.java.app.person;

import java.time.LocalDateTime;

public class Person {
    // ID
    private Integer personId;

    // 名前
    private String personName;

    // 年齢
    private Integer age;

    // 性別
    private String gender;

    // 最終更新時間
    private LocalDateTime lastUpdateTime;

    // 引数なしのコンストラクタ
    public Person() {}

    // コンストラクタ
    public Person(Integer personId, String personName, Integer age,
            String gender, LocalDateTime lastUpdateTime) {
        this.personId = personId;
        this.personName = personName;
        this.age = age;
        this.gender = gender;
        this.lastUpdateTime = lastUpdateTime;
    }

    // IDへのアクセサメソッド
    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    // 名前へのアクセサメソッド
    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    // 性別へのアクセサメソッド
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // 年齢へのアクセサメソッド
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    // 最終更新時間へのアクセサメソッド
    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return "Person [personId=" + personId + ", personName=" + personName + ", age="
                + age + ", gender=" + gender + ", lastUpdateTime=" + lastUpdateTime + "]";
    }
}