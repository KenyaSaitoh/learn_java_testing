package org.study.junit.old;

import junit.framework.TestCase;

public class TestExceptionCaseMain1 extends TestCase {

    public TestExceptionCaseMain1(String name) {
        super(name);
    }

    // Exceptionが発生した場合は、テストの失敗を通知する。
    public void testException() {
        TestTarget target = new TestTarget();
        try {
            target.executeWithException(-1);
        } catch (Exception e) {
            fail();
        }
    }
}