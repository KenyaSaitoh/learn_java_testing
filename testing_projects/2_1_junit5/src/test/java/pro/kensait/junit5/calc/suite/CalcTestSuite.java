package pro.kensait.junit5.calc.suite;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import pro.kensait.junit5.calc.normal.CalculatorTest_1;
import pro.kensait.junit5.calc.normal.CalculatorTest_2;

/*
 * Calculatorのテストクラスをグループ化するためのテストスイート
 * クラスを列挙するパターン
 */
@Suite
@SelectClasses({ CalculatorTest_1.class, CalculatorTest_2.class })
public class CalcTestSuite {
    // このクラスはテストスイートを構成するためだけに使用され、通常は追加のコードは含まれない
}
