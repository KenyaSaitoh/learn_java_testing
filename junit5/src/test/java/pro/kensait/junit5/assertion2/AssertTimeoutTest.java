package pro.kensait.junit5.assertion2;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import pro.kensait.junit5.assertion.ComplexService;

/*
 * タイムアウトを検証するためのテストクラス
 */
@Tag("excludeFromBuild") // ビルドテスト時に対象外にするためにタグを付与する
public class AssertTimeoutTest {

    // タイムアウトすることを検証する
    @Test
    void test_Timeout() {
        ComplexService cs = new ComplexService();
        assertTimeout(Duration.ofMillis(3000), () -> {
            cs.process();
        });
    }
}