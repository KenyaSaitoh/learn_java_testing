package pro.kensait.spring.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

/*
 * テスト用プロファイルへの切り替えテストクラス
 */
@SpringBootTest
@ActiveProfiles("local")
public class ProfileTest {
    @Autowired
    private Environment env;

    @Test
    @DisplayName("テスト用プロファイルへの切り替えをテストする")
    void test_Read_TestProperty() {
        System.out.println("spring.datasource.url => " +
                env.getProperty("spring.datasource.url"));
        System.out.println("spring.datasource.username => " +
                env.getProperty("spring.datasource.username"));
        System.out.println("spring.datasource.password => " +
                env.getProperty("spring.datasource.password"));
    }
}