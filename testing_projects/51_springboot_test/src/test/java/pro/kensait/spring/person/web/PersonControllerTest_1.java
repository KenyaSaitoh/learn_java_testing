package pro.kensait.spring.person.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import pro.kensait.spring.person.service.Person;

/*
 * PersonControllerを対象にした結合テストクラス
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class PersonControllerTest_1 {
    // インジェクションポイント
    @Autowired
    private MockMvc mockMvc;

    /*
     * この方法はデータが書き換わってしまうため、毎回クリーンアップが必要！！！
     */
    @Test
    @DisplayName("トップ画面への遷移をテストする")
    public void test_ViewPersonList() throws Exception {
        // 期待値を生成する
        Person alice = new Person(1, "Alice", 25, "female");
        Person bob = new Person(2, "Bob", 35, "male");
        Person carol = new Person(3, "Carol", 30, "female");
        Person dave = new Person(4, "Dave", 23, "male");
        Person ellen = new Person(5, "Ellen", 33, "male");
        List<Person> personList = Arrays.asList(alice, bob, carol, dave, ellen);

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
}
