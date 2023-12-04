package pro.kensait.spring.person.rest.server.api;

public class Person {
    private Integer personId;
    private String personName;
    private Integer age;
    private String gender;

    // 引数のないコンストラクタ
    public Person() {}

    // コンストラクタ
    public Person(Integer personId, String personName, Integer age, String gender) {
        this.personId = personId;
        this.personName = personName;
        this.age = age;
        this.gender = gender;
    }

    public Person(String personName, Integer age, String gender) {
        this.personName = personName;
        this.age = age;
        this.gender = gender;
    }

    // アクセサメソッド
    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person [personId=" + personId + ", personName=" + personName + ", age="
                + age + ", gender=" + gender + "]";
    }
}