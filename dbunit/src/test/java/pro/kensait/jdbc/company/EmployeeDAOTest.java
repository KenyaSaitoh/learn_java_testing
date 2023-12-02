package pro.kensait.jdbc.company;

import static org.junit.jupiter.api.Assertions.*;
import static pro.kensait.jdbc.util.DatabaseUtil.*;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.List;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * ShippingServiceを対象にしたテストクラス
 */
public class EmployeeDAOTest {
    private static final String INIT_DATA = "src/test/resources/INIT_DATA";
    private static final String EXPECTED_DATA_1 = "src/test/resources/EXPECTED_DATA_1";
    //private static final String EXPECTED_DATA_FILE_1 = "src/test/resources/expected-data-1.csv";

    /*
     *  すべてのテストメソッドに共通的な変数はフィールドとして宣言する
     */

    // テスト対象クラス
    EmployeeDAO employeeDAO;

    // 共通的な変数
    Connection conn;

    // DBユニットのための変数
    IDatabaseTester databaseTester;

    /*
     *  各テストメソッド呼び出しの前処理（共通変数の初期化など）
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
        // Connectionを取得する
        conn = DriverManager.getConnection(url, user, password);

        // IDatabaseTesterを初期化する
        databaseTester = new JdbcDatabaseTester(driver, url, user, password);

        // CSVファイルから初期データを読み込む
        IDataSet dataSet = new CsvDataSet(new File(INIT_DATA));
        databaseTester.setDataSet(dataSet);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.onSetup();
    }

    /*
     * 主キー検索の結果をテストする
     */
    @Test
    void test_FindEmployee() {
        // テスト実行し、「実際の値」を取得する
        EmployeeDAO employeeDAO = new EmployeeDAO(conn);
        Employee actual = employeeDAO.findEmployee(10001);

        // 「期待値」を生成する
        Employee expected = new Employee(10001, "Alice", "SALES" ,LocalDate.of(2012, 4, 1),
                "MANAGER", 500000);

        // 「期待値」と「実際の値」が一致しているかを検証する
        assertEquals(expected, actual);
    }

    /*
     * 条件検索の結果をテストする
     */
    @Test
    void test_FindEmployeesBySalary() throws Exception {
        // テスト実行し、「実際の値」を取得する
        EmployeeDAO employeeDAO = new EmployeeDAO(conn);
        List<Employee> actual = employeeDAO.findEmployeesBySalary(300000, 400000);

        // 「期待値（サイズ）」と「実際の値（サイズ）」が一致しているかを検証する
        assertEquals(4, actual.size());
    }
}