package org.study.junit.old;

import junit.framework.TestCase;

public class TestExceptionCaseMain extends TestCase {

    public TestExceptionCaseMain(String name) {
        super(name);
    }

    public void testException() {
        TestTarget target = new TestTarget();
        try {
            target.executeWithException(-1);

            // 例外が発生しなかった場合は、意図的にテストの失敗を通知する。
            fail();

        } catch (Exception expected) {
            // 例外が発生した場合は、テスト自体は成功。
            expected.printStackTrace();
        }
    }
}