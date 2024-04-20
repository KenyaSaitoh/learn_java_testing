package pro.kensait.spring.person.web;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import pro.kensait.spring.person.service.Person;
import pro.kensait.spring.person.service.PersonService;

/*
 * PersonControllerを対象にした結合テストクラス
 */
@WebMvcTest(PersonController.class)
public class PersonControllerTest {
    // インジェクションポイント
    @Autowired
    private MockMvc mockMvc;

    // テスト対象クラスの呼び出し先（モック化対象）
    @MockBean
    private PersonService personService;

    @Test
    @DisplayName("トップ画面への遷移をテストする")
    public void test_ViewPersonList() throws Exception {
        // モック化されたPersonServiceの振る舞いを設定する
        Person alice = new Person(1, "Alice", 25, "female");
        Person bob = new Person(2, "Bob", 35, "male");
        Person carol = new Person(3, "Carol", 30, "female");
        List<Person> personList = Arrays.asList(alice, bob, carol);
        when(personService.getPersonsAll()).thenReturn(personList);

        // テストを実行し、検証する
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/viewList"))
                .andExpect(redirectedUrl("/viewList"));

        // テストを実行し、検証する（リダイレクト後）
        mockMvc.perform(get("/viewList"))
                .andExpect(status().isOk())
                .andExpect(view().name("PersonTablePage"))
                .andExpect(model().attributeExists("personList"))
                .andExpect(model().attribute("personList", personList));
    }

    @Test
    @DisplayName("入力画面への遷移をテストする")
    public void test_toCreate() throws Exception {
        // テストを実行し、検証する
        mockMvc.perform(post("/toCreate"))
                .andExpect(status().isOk())
                .andExpect(view().name("PersonInputPage"));
    }

    @Test
    @DisplayName("新規人物作成と確認画面への遷移をテストする")
    public void test_toConfirm() throws Exception {
        // 入力値をセットアップする
        PersonSession personSession = new PersonSession();
        personSession.setPersonName("Dave");
        personSession.setAge(23);
        personSession.setGender("male");

        // テストを実行し、検証する
        mockMvc.perform(post("/toConfirm")
                .sessionAttr("personSession", personSession)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("PersonUpdatePage"));
    }

    @Test
    @DisplayName("新規人物作成におけるバリデーションエラーのテスト")
    public void test_toConfirm_ValidationError() throws Exception {
        // 入力値をセットアップする
        PersonSession personSession = new PersonSession();
        personSession.setPersonName("DaveDaveDaveDaveDaveDave"); // 20字超
        personSession.setAge(10); // 20未満
        personSession.setGender(""); // 空文字

        // テストを実行し、検証する
        mockMvc.perform(post("/toConfirm")
                .sessionAttr("personSession", personSession)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("PersonInputPage"))
                .andExpect(model().hasErrors());
    }
    
    @Test
    @DisplayName("人物の更新をテストする")
    void test_UpdatePerson() throws Exception {
        // モック化されたPersonServiceの振る舞いを設定する
        Person alice = new Person(1, "Alice", 26, "female");
        Person bob = new Person(2, "Bob", 35, "male");
        Person carol = new Person(3, "Carol", 30, "female");
        List<Person> personList = Arrays.asList(alice, bob, carol);
        when(personService.getPersonsAll()).thenReturn(personList);

        // 入力値をセットアップする
        PersonSession personSession = new PersonSession();
        personSession.setPersonId(1);
        personSession.setPersonName("Alice");
        personSession.setAge(26);
        personSession.setGender("female");

        // テストを実行し、検証する
        mockMvc.perform(post("/update")
                .sessionAttr("personSession", personSession)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/viewList"))
                .andExpect(redirectedUrl("/viewList"));

        // テストを実行し、検証する（リダイレクト後）
        mockMvc.perform(get("/viewList"))
                .andExpect(status().isOk())
                .andExpect(view().name("PersonTablePage"))
                .andExpect(model().attributeExists("personList"))
                .andExpect(model().attribute("personList", personList));

        // モックの指定されたメソッド呼び出しが一度だけ行われたことを検証する
        verify(personService).updatePerson(any(Person.class));
    }

    @Test
    @DisplayName("人物の追加をテストする")
    void test_AddPerson() throws Exception {
        // モック化されたPersonServiceの振る舞いを設定する
        Person alice = new Person(1, "Alice", 25, "female");
        Person bob = new Person(2, "Bob", 35, "male");
        Person carol = new Person(3, "Carol", 30, "female");
        Person dave = new Person(4, "Dave", 23, "male");
        List<Person> personList = Arrays.asList(alice, bob, carol, dave);
        when(personService.getPersonsAll()).thenReturn(personList);

        // 入力値をセットアップする
        PersonSession personSession = new PersonSession();
        personSession.setPersonName("Dave");
        personSession.setAge(23);
        personSession.setGender("male");

        // テストを実行し、検証する
        mockMvc.perform(post("/update")
                .sessionAttr("personSession", personSession)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/viewList"))
                .andExpect(redirectedUrl("/viewList"));

        // テストを実行し、検証する（リダイレクト後）
        mockMvc.perform(get("/viewList"))
                .andExpect(status().isOk())
                .andExpect(view().name("PersonTablePage"))
                .andExpect(model().attributeExists("personList"))
                .andExpect(model().attribute("personList", personList));

        // モックの指定されたメソッド呼び出しが一度だけ行われたことを検証する
        Person dave2 = new Person("Dave", 23, "male");
        verify(personService).createPerson(dave2);
    }

    @Test
    @DisplayName("人物の編集をテストする")
    void test_EditPerson() throws Exception {
        // モック化されたPersonServiceの振る舞いを設定する
        Person alice = new Person(1, "Alice", 25, "female");
        when(personService.getPerson(1)).thenReturn(alice);

        // テストを実行し、検証する
        mockMvc.perform(post("/edit")
                .param("personId", String.valueOf(1)))
                .andExpect(status().isOk())
                .andExpect(view().name("PersonInputPage"))
                .andExpect(model().attributeExists("personSession"));
    }

    @Test
    @DisplayName("人物の削除をテストする")
    public void test_RemovePerson() throws Exception {
        // モック化されたPersonServiceの振る舞いを設定する
        Person alice = new Person(1, "Alice", 25, "female");
        Person bob = new Person(2, "Bob", 35, "male");
        List<Person> personList = Arrays.asList(alice, bob);
        when(personService.getPersonsAll()).thenReturn(personList);

        // テストを実行し、検証する
        mockMvc.perform(post("/remove")
                .param("personId", String.valueOf(3)))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/viewList"))
                .andExpect(redirectedUrl("/viewList"));

        // テストを実行し、検証する（リダイレクト後）
        mockMvc.perform(get("/viewList"))
                .andExpect(status().isOk())
                .andExpect(view().name("PersonTablePage"))
                .andExpect(model().attributeExists("personList"))
                .andExpect(model().attribute("personList", personList));

        // モックの指定されたメソッド呼び出しが一度だけ行われたことを検証する
        verify(personService).removePerson(3);
    }

    @Test
    @DisplayName("入力画面への遷移をテストする")
    public void test_Back() throws Exception {
        // テストを実行し、検証する
        mockMvc.perform(get("/back"))
                .andExpect(status().isOk())
                .andExpect(view().name("PersonInputPage"));
    }
}
