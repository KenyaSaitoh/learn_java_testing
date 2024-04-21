package pro.kensait.spring.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import pro.kensait.spring.property.PropertyHolder;

/*
 * テスト用プロパティの読み込みテストクラス
 */
@SpringBootTest
@ContextConfiguration(classes = PropertyHolder.class)
@TestPropertySource(properties = {
        "property.value.1 = 100"
})
@TestPropertySource(locations = "classpath:config-test.properties")
public class PropertyTest {
    @Autowired
    private PropertyHolder propsHolder;

    @Test
    @DisplayName("テスト用プロパティの読み込みテスト")
    void test_Read_TestProperty() {
        System.out.println(propsHolder.getProp1());
        System.out.println(propsHolder.getProp2());
    }
}