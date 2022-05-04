package org.study.junit.old;

import junit.framework.TestCase;

public class TestExceptionCaseMain2 extends TestCase {

    public TestExceptionCaseMain2(String name) {
        super(name);
    }

    // Exceptionが発生しなかった場合は、テストの失敗を通知する。
    public void testException() {
        TestTarget target = new TestTarget();
        try {
            target.executeWithException(10);
            fail();
        } catch (Exception e) {
        }
    }
}