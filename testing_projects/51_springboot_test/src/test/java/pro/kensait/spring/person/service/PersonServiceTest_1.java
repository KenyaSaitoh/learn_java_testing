package pro.kensait.spring.person.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/*
 * PersonServiceを対象にした単体テストクラス
 */
@ExtendWith(SpringExtension.class)
public class PersonServiceTest_1 {
    // テスト対象クラス
    private PersonService personService;

    // テスト対象クラスの呼び出し先（モック化対象）
    @MockBean
    private PersonDAO personRepos;

    @BeforeEach
    void setUp() {
        // モック化されたPersonReposの振る舞いを設定する
        Person alice = new Person(1, "Alice", 25, "female");
        when(personRepos.find(anyInt())).thenReturn(alice);

        // テスト対象クラスのインスタンスを直接生成し、セットアップする
        personService = new PersonService(personRepos);
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
}
