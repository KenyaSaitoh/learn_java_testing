package pro.kensait.spring.person.web;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

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
 * PersonControllerを対象にした単体テストクラス
 */
@WebMvcTest(PersonController.class)
public class PersonControllerTest {
    // MockMvcをインジェクションする
    @Autowired
    private MockMvc mockMvc;

    // テスト対象クラスの呼び出し先（モック化対象）
    @MockBean
    private PersonService personService;

    @Test
    @DisplayName("トップ画面への遷移をテストする")
    void test_ViewPersonList() throws Exception {
        // モック化されたPersonServiceの振る舞いを設定する
        Person alice = new Person(1, "Alice", 25, "female");
        Person bob = new Person(2, "Bob", 35, "male");
        Person carol = new Person(3, "Carol", 30, "female");
        List<Person> personList = Arrays.asList(alice, bob, carol);
        when(personService.getPersonsAll()).thenReturn(personList);

        // テストを実行し、検証する
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
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
    void test_toCreate() throws Exception {
        // テストを実行し、検証する
        mockMvc.perform(post("/toCreate"))
                .andExpect(status().isOk())
                .andExpect(view().name("PersonInputPage"))
                .andExpect(request().sessionAttributeDoesNotExist("personSession"));
    }

    @Test
    @DisplayName("新規人物作成と確認画面への遷移をテストする")
    void test_toConfirm() throws Exception {
        PersonSession personSession = new PersonSession("Dave", 23, "male");

        // テストを実行し、検証する
        mockMvc.perform(post("/toConfirm")
                .param("personName", "Dave")
                .param("age", "23")
                .param("gender", "male")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("PersonUpdatePage"))
                .andExpect(model().hasNoErrors())
                .andExpect(request().sessionAttribute("personSession", personSession));
    }

    @Test
    @DisplayName("新規人物作成における入力エラーをテストする")
    void test_toConfirm_ValidationError() throws Exception {
        // テストを実行し、検証する
        mockMvc.perform(post("/toConfirm")
                .param("personName", "DaveDaveDaveDaveDaveDave") // 20字超
                .param("age", "10") // 20未満
                .param("gender", "") // 空文字
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("PersonInputPage"))
                .andExpect(model().attributeHasFieldErrors(
                        "personSession", "personName", "age", "gender"));
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
        PersonSession personSession = new PersonSession(1, "Alice", 26, "female");

        // テストを実行し、検証する
        mockMvc.perform(post("/update")
                .sessionAttr("personSession", personSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/viewList"));

        // テストを実行し、検証する（リダイレクト後）
        mockMvc.perform(get("/viewList"))
                .andExpect(status().isOk())
                .andExpect(view().name("PersonTablePage"))
                .andExpect(model().attributeExists("personList"))
                .andExpect(model().attribute("personList", personList));

        // モックの指定されたメソッド呼び出しが一度だけ行われたことを検証する
        verify(personService).updatePerson(any(Person.class));

        // 新規作成画面に移動したとき、セッション属性が削除されていることを検証する
        mockMvc.perform(post("/toCreate"))
               .andExpect(request().sessionAttributeDoesNotExist("personSession"));
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
        PersonSession personSession = new PersonSession("Dave", 23, "male");

        // テストを実行し、検証する
        mockMvc.perform(post("/update")
                .sessionAttr("personSession", personSession))
                .andExpect(status().is3xxRedirection())
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

        // 新規作成画面に移動したとき、セッション属性が削除されていることを検証する
        mockMvc.perform(post("/toCreate"))
                .andExpect(request().sessionAttributeDoesNotExist("personSession"));
    }

    @Test
    @DisplayName("人物の編集をテストする")
    void test_EditPerson() throws Exception {
        // モック化されたPersonServiceの振る舞いを設定する
        Person alice = new Person(1, "Alice", 25, "female");
        when(personService.getPerson(1)).thenReturn(alice);

        // テストを実行し、検証する
        mockMvc.perform(post("/edit")
                .param("personId", "1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("PersonInputPage"))
                .andExpect(model().attributeHasNoErrors("personSession"));
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
                .param("personId", "3")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
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
