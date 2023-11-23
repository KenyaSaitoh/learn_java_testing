package pro.kensait.java.hamcrest;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class HamcrestLibMatchersTest1 {

    // 空文字であることを検証する
    @Test
    public void test01() {
        String actual = "";
        assertThat(actual, isEmptyString());
    }

    // 空文字またはnullであることを検証する
    @Test
    public void test02() {
        String actual = "";
        assertThat(actual, isEmptyOrNullString());
    }

    // 大文字・小文字を区別せずに比較する
    @Test
    public void test03() {
        String actual = "Foo";
        assertThat(actual, equalToIgnoringCase("foo"));
    }

    // 大文字・小文字・前後のブランクを無視して比較する
    @Test
    public void test04() {
        String actual = " Foo ";
        assertThat(actual, equalToIgnoringWhiteSpace("foo"));
    }

    // 指定された範囲内であることを検証する
    @Test
    public void test05() {
        double actual = 10;
        assertThat(actual, closeTo(5, 15));
    }

    // 指定された値より大きいことを検証する
    @Test
    public void test06() {
        int actual = 10;
        assertThat(actual, greaterThan(9));
    }

    // 指定された値以上であることを検証する
    @Test
    public void test07() {
        int actual = 10;
        assertThat(actual, greaterThanOrEqualTo(10));
    }

    // 指定された値より小さいことを検証する
    @Test
    public void test08() {
        int actual = 10;
        assertThat(actual, lessThan(11));
    }

    // 指定された値以下であることを検証する
    @Test
    public void test09() {
        int actual = 10;
        assertThat(actual, lessThanOrEqualTo(10));
    }
}