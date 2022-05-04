package org.study.junit.old;

import junit.framework.TestCase;

public class TestCaseMain1 extends TestCase {

    public TestCaseMain1(String name) {
        super(name);
    }

    public void testGetName() {
        TestTarget target = new TestTarget("Foo", 1000);
        String name = target.getName();
        assertEquals("Check Name", "Foo", name);
    }

    public void testSetNumber() {
        TestTarget target = new TestTarget("Bar", 5000);
        target.setNumber(10);
        int number = target.getNumber();
        assertEquals("Check Number", 10, number);
    }
}