package org.study.junit.basic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FooBeanTest {
    private Foo foo;
    private Bar bar;

    // テストメソッドごとに事前実行
    @Before
    public void before() {
        bar = new BarBean();
        foo = new FooBean(bar);
    }

    @Test
    public void test01() {
        int result = foo.doBusiness(100, 50);
        assertEquals(4, result);
    }

    @Test
    public void test02() {
        try {
            foo.doBusiness(100, 0);
            // 例外が発生しなかった場合は、意図的にテストの失敗を通知する。
            fail();
        } catch (Exception e) {
        }
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void test03() throws Throwable {
        exception.expect(ArithmeticException.class);
        // exception.expect(IllegalArgumentException.class);
        foo.doBusiness(100, 0);
    }
}
