package pro.kensait.junit5.assertion4;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import pro.kensait.junit5.assertion.ComplexService;

/*
 * タイムアウトを検証するためのテストクラス
 */
@Tag("excludeFromBuild") // ビルドテスト時に対象外にするためにタグを付与する
public class TimeoutTest {

    @Test
    @DisplayName("タイムアウトすることを検証する")
    void test_Timeout() {
        ComplexService cs = new ComplexService();
        assertTimeout(Duration.ofMillis(3000), () -> {
            cs.process();
        });
    }
}