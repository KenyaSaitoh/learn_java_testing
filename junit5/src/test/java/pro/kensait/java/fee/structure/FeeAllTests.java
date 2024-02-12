package pro.kensait.java.fee.structure;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ FeeOurBankTest.class, FeeOtherBankTest.class })
public class FeeAllTests {
    // このクラスはテストスイートを構成するためだけに使用され、
    // 通常は追加のコードは含まれません。
}
