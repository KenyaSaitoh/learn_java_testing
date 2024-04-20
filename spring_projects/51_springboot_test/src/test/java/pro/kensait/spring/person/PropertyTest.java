package pro.kensait.spring.person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/*
 * テスト用プロパティの読み込みテストクラス
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb"
})
@TestPropertySource(locations = "classpath:application-test.properties")
public class PropertyTest {
    @Autowired
    private Environment env;

    @Test
    @DisplayName("テスト用プロパティの読み込みテスト")
    void test_TestProperty() {
        System.out.println(env.getProperty("spring.datasource.url"));
        System.out.println(env.getProperty("spring.datasource.username"));
        System.out.println(env.getProperty("spring.datasource.password"));
    }
}