package pro.kensait.jdbc.company;

import static pro.kensait.jdbc.util.DatabaseUtil.*;

import java.sql.Connection;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.DefaultTable;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.BeforeEach;

/*
 * ShippingServiceを対象にしたテストクラス
 */
public class SetUpTruncateTest {
    private static final String EXPECTED_DATA_FILE = "src/test/resources/expected-data.xml";

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
    public void setUpDataBase() throws Exception {
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

        // 空のデータセットを生成する
        IDataSet emptyDataSet = new DefaultDataSet(new DefaultTable("EMPLOYEE"));

        // テーブルを初期化（全件削除）する
        databaseTester.setDataSet(emptyDataSet);
        databaseTester.setSetUpOperation(DatabaseOperation.TRUNCATE_TABLE);
        databaseTester.onSetup();
    }
}