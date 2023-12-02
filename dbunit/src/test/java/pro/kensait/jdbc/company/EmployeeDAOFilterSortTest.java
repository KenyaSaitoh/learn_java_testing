package pro.kensait.jdbc.company;

// これを先にimportすることが重要！
import static org.dbunit.Assertion.*;
import static pro.kensait.jdbc.util.DatabaseUtil.*;

import java.io.File;
import java.sql.Connection;
import java.time.LocalDate;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.csv.CsvDataSet;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * ShippingServiceを対象にしたテストクラス
 */
public class EmployeeDAOFilterSortTest {
    private static final String INIT_DATA = "src/test/resources/INIT_DATA";
    private static final String EXPECTED_DATA_DIR_1 = "src/test/resources/EXPECTED_DATA_1";

    /*
     *  すべてのテストメソッドに共通的な変数はフィールドとして宣言する
     */

    // テスト対象クラス
    EmployeeDAO employeeDAO;

    // DBユニットのための変数
    IDatabaseTester databaseTester;
    IDatabaseConnection databaseConnection;

    // 共通的な変数（EmployeeDAOを動作させるためにjava.sql.Connectionが必要）
    Connection jdbcConnection;

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
        IDataSet dataSet = new CsvDataSet(new File(INIT_DATA));
        databaseTester.setDataSet(dataSet);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.onSetup();
    }

    /*
     * 挿入の結果をテストする
     */
    @Test
    void test_InsertEmployee() throws Exception {
        // テスト実行し、「実際の値」を取得する
        EmployeeDAO employeeDAO = new EmployeeDAO(jdbcConnection);
        Employee employee = new Employee(10021, "Steve", "SALES", LocalDate.of(2017, 10, 1),
                null, 380000);
        employeeDAO.insertEmployee(employee);

        // ORDER_DATE_TIME列を検証の対象外にするために、配列を用意用意する
        String[] excludedColumns = new String[]{"ENTRANCE_DATE"};

        // 「期待値」となるテーブルを（ENTRANCE_DATE列除く）取得する（CSVファイルから）
        IDataSet expectedDataSet = new CsvDataSet(new File(EXPECTED_DATA_DIR_1));
        ITable expectedTable = DefaultColumnFilter.excludedColumnsTable(
                expectedDataSet.getTable("EMPLOYEE"), excludedColumns);

        // 「実際の値」となるテーブル（ENTRANCE_DATE列除く）を取得する
        IDataSet databaseDataSet = databaseTester.getConnection().createDataSet();
        ITable actualTable = DefaultColumnFilter.excludedColumnsTable(
                databaseDataSet.getTable("EMPLOYEE"), excludedColumns);

        // 「実際の値」をソートする（必要に応じて）
        String[] columns = {"EMPLOYEE_ID"};
        ITable sortedActualTable = new SortedTable(actualTable, columns);

        // 「期待値」と「実際の値」が一致しているかを検証する
        assertEquals(expectedTable, sortedActualTable);
    }
}