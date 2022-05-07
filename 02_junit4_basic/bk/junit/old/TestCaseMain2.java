package org.study.junit.old;

import junit.framework.TestCase;

public class TestCaseMain2 extends TestCase {

    private TestTarget target;

    public TestCaseMain2(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        // フィールドとして宣言したテスト対象クラスを生成する。
        target = new TestTarget("Foo", 1000);
    }

    public void testGetName() {
        String name = target.getName();
        assertEquals("Check Name", "Foo", name);
    }

    public void testSetNumber() {
        target.setNumber(10);
        int number = target.getNumber();
        assertEquals("Check Number", 10, number);
    }

    public void testCalc() {
        // testSetNumberメソッドでセットされた値とは無関係にテスト可能。
        int output = target.calc(5);
        assertEquals("Check Calc", 5000, output);
    }
}