package pro.kensait.jdbc.company;

import static org.dbunit.Assertion.assertEquals; // これを先にimportすることが重要！
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static pro.kensait.jdbc.util.DatabaseUtil.*;

import java.io.File;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.csv.CsvDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * ShippingServiceを対象にしたテストクラス
 */
public class EmployeeDAOTest {
    // DBUnitが使用するデータ格納ディレクトリ
    private static final String INIT_DATA_DIR = "src/test/resources/INIT_DATA";
    private static final String EXPECTED_DATA_DIR_1 = "src/test/resources/EXPECTED_DATA_1";
    private static final String EXPECTED_DATA_DIR_2 = "src/test/resources/EXPECTED_DATA_2";
    private static final String EXPECTED_DATA_DIR_3 = "src/test/resources/EXPECTED_DATA_3";

    /*
     *  各テストケースで共通的なフィクスチャを、フィールドとして宣言する
     */

    // テスト対象クラス
    EmployeeDAO employeeDAO;

    // DBUnitのためのフィクスチャ
    IDatabaseTester databaseTester;
    IDatabaseConnection databaseConnection;

    // 各テストメソッドで共通的なフィクスチャ
    Connection jdbcConnection; // EmployeeDAOを動作させるためにjava.sql.Connectionが必要

    /*
     *  データベースやDBUnitを初期化する
     */
    @BeforeEach
    public void setUpDatabase() throws Exception {
        // プロパティファイルよりデータベース情報を取得する
        String driver = getProperty("jdbc.driver");
        String url = getProperty("jdbc.url");
        String user = getProperty("jdbc.user");
        String password = getProperty("jdbc.password");

        try {
            // JDBCドライバをロードする
            Class.forName(driver);
        } catch(ClassNotFoundException cnfe) {
            throw new RuntimeException(cnfe);
        }

        // IDatabaseTesterを初期化する
        databaseTester = new JdbcDatabaseTester(driver, url, user, password);

        // DatabaseConnectionを取得する
        databaseConnection = databaseTester.getConnection();

        // Connectionを取得する
        jdbcConnection = databaseConnection.getConnection();

        // CSVファイルから初期データを読み込む
        IDataSet dataSet = new CsvDataSet(new File(INIT_DATA_DIR));
        databaseTester.setDataSet(dataSet);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.onSetup();
    }

    @Test
    @DisplayName("主キー検索の結果をテストする")
    void test_SelectEmployee() {
        // テスト実行し、実測値を取得する
        EmployeeDAO employeeDAO = new EmployeeDAO(jdbcConnection);
        Employee actual = employeeDAO.selectEmployee(10001);

        // 期待値を生成する
        Employee expected = new Employee(10001, "Alice", "SALES" ,LocalDate.of(2012, 4, 1),
                "MANAGER", 500000);

        // 期待値と実測値が一致しているかを検証する
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("条件検索の結果をテストする")
    void test_SelectEmployeesBySalary() throws Exception {
        // テスト実行し、実測値を取得する
        EmployeeDAO employeeDAO = new EmployeeDAO(jdbcConnection);
        List<Employee> actualList = employeeDAO.selectEmployeesBySalary(300000, 400000);

        // 「期待値リスト」のサイズと「実測値リスト」のサイズが一致しているかを検証する
        assertEquals(4, actualList.size());

        // 「期待値リスト」を生成する
        List<Employee> expectedList = List.of(
                new Employee(10003, "Carol", "HR" ,LocalDate.of(2012, 4, 1), "CHIEF", 350000),
                new Employee(10004, "Dave", "SALES" ,LocalDate.of(2012, 4, 1), "LEADER", 400000),
                new Employee(10005, "Ellen", "SALES" ,LocalDate.of(2013, 4, 1), "CHIEF", 300000),
                new Employee(10011, "Oscar", "PRODUCT" ,LocalDate.of(2015, 11, 1), "CHIEF", 320000));

        // 「実測値リスト」をソートする
        Collections.sort(actualList, (e1, e2) -> {
            if (e1.getEmployeeId() < e2.getEmployeeId()) return -1;
            if (e1.getEmployeeId() > e2.getEmployeeId()) return 1;
            return 0;
         });

        // 「期待値リスト」と「実測値リスト」が一致しているかを検証する
        assertIterableEquals(expectedList, actualList);
    }

    @Test
    @DisplayName("挿入の結果をテストする")
    void test_InsertEmployee() throws Exception {
        // テスト実行し、実測値を取得する
        EmployeeDAO employeeDAO = new EmployeeDAO(jdbcConnection);
        Employee employee = new Employee(10021, "Steve", "SALES", LocalDate.of(2017, 10, 1),
                null, 380000);
        employeeDAO.insertEmployee(employee);

        // 期待値となるテーブルを取得する（CSVファイルから）
        IDataSet expectedDataSet = new CsvDataSet(new File(EXPECTED_DATA_DIR_1));
        ITable expectedTable = expectedDataSet.getTable("EMPLOYEE");

        // 実測値となるテーブルを取得する
        IDataSet databaseDataSet = databaseTester.getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("EMPLOYEE");

        // 期待値と実測値が一致しているかを検証する
        assertEquals(expectedTable, actualTable);
    }

    @Test
    @DisplayName("削除の結果をテストする")
    void test_DeleteEmployee() throws Exception {
        // テスト実行し、実測値を取得する
        EmployeeDAO employeeDAO = new EmployeeDAO(jdbcConnection);
        employeeDAO.deleteEmployee(10004);

        // 期待値となるテーブルを取得する（CSVファイルから）
        IDataSet expectedDataSet = new CsvDataSet(new File(EXPECTED_DATA_DIR_2));
        ITable expectedTable = expectedDataSet.getTable("EMPLOYEE");

        // 実測値となるテーブルを取得する
        IDataSet databaseDataSet = databaseTester.getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("EMPLOYEE");

        // 期待値と実測値が一致しているかを検証する
        assertEquals(expectedTable, actualTable);
    }

    @Test
    @DisplayName("一括更新の結果をテストする")
    void test_UpdateSalary() throws Exception {
        // テスト実行し、実測値を取得する
        EmployeeDAO employeeDAO = new EmployeeDAO(jdbcConnection);
        employeeDAO.updateEmployeeSalary("SALES", 3000);

        // 期待値となるテーブルを取得する（CSVファイルから）
        IDataSet expectedDataSet = new CsvDataSet(new File(EXPECTED_DATA_DIR_3));
        ITable expectedTable = expectedDataSet.getTable("EMPLOYEE");

        // 実測値となるテーブルを取得する
        IDataSet databaseDataSet = databaseTester.getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("EMPLOYEE");

        // 期待値と実測値が一致しているかを検証する
        assertEquals(expectedTable, actualTable);
    }
}