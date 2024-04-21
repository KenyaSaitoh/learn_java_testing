package pro.kensait.spring.person.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/*
 * PersonServiceを対象にした単体テストクラス
 */
@SpringBootTest
public class PersonServiceTest {
    // テスト対象クラス（インジェクション）
    @Autowired
    private PersonService personService;

    // テスト対象クラスの呼び出し先（モック化対象）
    @MockBean
    private PersonRepos personRepos;

    @BeforeEach
    void setUp() {
        // モック化されたPersonReposの振る舞いを設定する
        Person alice = new Person(1, "Alice", 25, "female");
        Person bob = new Person(2, "Bob", 35, "male");
        Person carol = new Person(3, "Carol", 30, "female");
        List<Person> all = Arrays.asList(alice, bob, carol);
        when(personRepos.find(anyInt())).thenReturn(alice);
        when(personRepos.findAll()).thenReturn(all);
        when(personRepos.findByLowerAge(anyInt())).thenAnswer(i -> {
            int age = i.getArgument(0);
            return all.stream().filter(p -> age <= p.getAge()).collect(Collectors.toList());
        });
        when(personRepos.save(any(Person.class))).thenReturn(4); // 新しいIDを返す
        when(personRepos.delete(anyInt())).thenReturn(1); // 1件削除されたと返す
        when(personRepos.update(any(Person.class))).thenReturn(1); // 1件更新されたと返す
        when(personRepos.updateAge(anyInt(), anyInt())).thenReturn(1); // 年齢が更新されたと返す
    }

    @Test
    @DisplayName("PersonService#getPerson()のテスト")
    void test_GetPerson() {
        // テスト実行し、実測値を取得する
        Person actual = personService.getPerson(1);

        // 期待値を生成する
        Person expected = new Person(1, "Alice", 25, "female");

        // 期待値と実測値が一致しているかを検証する
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("PersonService#getPersonAll()のテスト")
    void test_getPersonsAll() {
        // テスト実行し、実測値を取得する
        List<Person> actual = personService.getPersonsAll();

        // 期待値を生成する
        Person alice = new Person(1, "Alice", 25, "female");
        Person bob = new Person(2, "Bob", 35, "male");
        Person carol = new Person(3, "Carol", 30, "female");
        List<Person> expected = Arrays.asList(alice, bob, carol);

        // 期待値と実測値が一致しているかを検証する
        assertIterableEquals(expected, actual);
    }

    @Test
    @DisplayName("PersonService#getPersonsByLowerAge()のテスト")
    void test_getPersonsByLowerAge() {
        // テスト実行し、実測値を取得する
        List<Person> actual = personService.getPersonsByLowerAge(27);

        // 期待値を生成する
        Person bob = new Person(2, "Bob", 35, "male");
        Person carol = new Person(3, "Carol", 30, "female");
        List<Person> expected = Arrays.asList(bob, carol);

        // 期待値と実測値が一致しているかを検証する
        assertIterableEquals(expected, actual);
    }

    @Test
    @DisplayName("PersonService#createPerson()のテスト")
    void test_createPerson() {
        // テスト実行する
        Person dave = new Person(4, "Dave", 23, "male");
        personService.createPerson(dave);

        // モックの指定されたメソッド呼び出しが一度だけ行われたことを検証する
        verify(personRepos).save(dave);
    }

    @Test
    @DisplayName("PersonService#removePerson()のテスト")
    void test_removePerson() {
        // テスト実行し、実測値を取得する
        int actual = personService.removePerson(3);

        // 期待値と実測値が一致しているかを検証する
        assertEquals(1, actual); 

        // モックの指定されたメソッド呼び出しが一度だけ行われたことを検証する
        verify(personRepos).delete(3);
    }

    @Test
    @DisplayName("PersonService#updatePerson()のテスト")
    void test_updatePerson() {
        // テスト実行し、実測値を取得する
        Person alice = new Person(1, "Alice", 26, "female");
        int actual = personService.updatePerson(alice);

        // 期待値と実測値が一致しているかを検証する
        assertEquals(1, actual); 

        // モックの指定されたメソッド呼び出しが一度だけ行われたことを検証する
        verify(personRepos).update(alice);
    }

    @Test
    @DisplayName("PersonService#updatePersonAge()のテスト")
    void test_updatePersonAge() {
        // テスト実行し、実測値を取得する
        Person bob = new Person(2, "Bob", 36, "male");
        int actual = personService.updatePersonAge(bob);

        // 期待値と実測値が一致しているかを検証する
        assertEquals(1, actual); 

        // モックの指定されたメソッド呼び出しが一度だけ行われたことを検証する
        verify(personRepos).updateAge(2, 36);
    }
}