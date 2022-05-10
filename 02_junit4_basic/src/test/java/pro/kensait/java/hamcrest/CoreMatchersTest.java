package pro.kensait.java.hamcrest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

public class CoreMatchersTest {

    // 同じ値であることを検証する
    @Test
    public void test01() {
        String actual = "foo";
        assertThat(actual, is("foo"));
    }

    // 同じ値でないことを検証する
    @Test
    public void test02() {
        String actual = "foo";
        assertThat(actual, is(not("bar")));
    }

    // 同じboolean値であることを検証する
    @Test
    public void test03() {
        boolean actual = true;
        assertThat(actual, is(true));
    }

    // nullであることを検証する
    @Test
    public void test04() {
        String actual = null;
        assertThat(actual, nullValue());
    }

    // nullでないことを検証する
    @Test
    public void test05() {
        String actual = "foo";
        assertThat(actual, notNullValue());
    }

    // 同じインスタンスであることを検証する
    @Test
    public void test06() {
        String actual = "foo";
        String expected = actual;
        assertThat(actual, sameInstance(expected));
    }

    // 指定したクラスのインスタンス型であることを検証する
    @Test
    public void test07() {
        Integer actual = 10;
        assertThat(actual, instanceOf(Number.class));
    }

    // 指定した文字で始まることを検証する
    @Test
    public void test08() {
        String actual = "foo";
        assertThat(actual, startsWith("f"));
    }

    // 指定した文字で終わることを検証する
    @Test
    public void test09() {
        String actual = "foo";
        assertThat(actual, endsWith("o"));
    }

    // 指定した文字が含まれることを検証する
    @Test
    public void test10() {
        String actual = "foofoo";
        assertThat(actual, containsString("of"));
    }
}
