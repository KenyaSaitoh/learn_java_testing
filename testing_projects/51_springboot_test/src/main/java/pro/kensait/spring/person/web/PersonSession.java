package pro.kensait.spring.person.web;

import java.util.Objects;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PersonSession {
    // ID
    private Integer personId;

    // 名前
    @NotEmpty
    @Size(min = 1, max = 20)
    private String personName;

    // 年齢
    @NotNull
    @Min(20)
    @Max(100)
    private Integer age;

    // 性別
    @NotEmpty
    private String gender;

    // コンストラクタ
    public PersonSession() {}

    public PersonSession(Integer personId, String personName, Integer age,
            String gender) {
        this.personId = personId;
        this.personName = personName;
        this.age = age;
        this.gender = gender;
    }

    public PersonSession(String personName, Integer age, String gender) {
        this.personName = personName;
        this.age = age;
        this.gender = gender;
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

    @Override
    public String toString() {
        return "Person [personId=" + personId + ", personName=" + personName
                + ", age=" + age + ", gender=" + gender + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, gender, personId, personName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PersonSession other = (PersonSession) obj;
        return Objects.equals(age, other.age) && Objects.equals(gender, other.gender)
                && Objects.equals(personId, other.personId)
                && Objects.equals(personName, other.personName);
    }
}