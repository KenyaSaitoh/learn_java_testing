package pro.kensait.jdbc.company;

import static pro.kensait.jdbc.util.DatabaseUtil.*;

import java.sql.Connection;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.DefaultTable;
import org.dbunit.dataset.IDataSet;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@DisplayName("EmployeeDAOを対象に、テーブルの初期化だけを行うテストクラス")
public class SetUpTruncateTest {
    // テスト対象クラス
    EmployeeDAO employeeDAO;

    // DBUnitのためのフィクスチャ
    IDatabaseTester databaseTester;
    IDatabaseConnection databaseConnection;

    // 各テストメソッドで共通的なテストフィクスチャ
    Connection jdbcConnection; // EmployeeDAOを動作させるためにjava.sql.Connectionが必要

    /*
     *  DBUnitのテストフィクスチャやデータベース上のデータを初期化する
     */
    @BeforeEach
    public void setUpDatabase() throws Exception {
        // プロパティファイルよりデータベース情報を取得する
        String driver = getProperty("jdbc.driver");
        String url = getProperty("jdbc.url");
        String user = getProperty("jdbc.user");
        String password = getProperty("jdbc.password");

        // IDatabaseTesterを初期化する
        databaseTester = new JdbcDatabaseTester(driver, url, user, password);

        // DatabaseConnectionを取得する
        databaseConnection = databaseTester.getConnection();

        // MariaDB（MySQL）用のDataTypeFactoryを設定する
        DatabaseConfig config = databaseConnection.getConfig();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());

        // Connectionを取得する
        jdbcConnection = databaseConnection.getConnection();

        // データを初期化する
        initData();
    }

    /*
     * データを初期化する
     */
    private void initData() throws Exception {
        // 空のデータセットを生成する
        IDataSet emptyDataSet = new DefaultDataSet(new DefaultTable("EMPLOYEE"));

        // データベースの対象テーブルを初期化（全件削除）する
        databaseTester.setDataSet(emptyDataSet);
        databaseTester.setSetUpOperation(DatabaseOperation.TRUNCATE_TABLE);
        databaseTester.onSetup();
    }
}