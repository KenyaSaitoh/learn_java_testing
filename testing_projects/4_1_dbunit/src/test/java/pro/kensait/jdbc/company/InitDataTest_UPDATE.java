package pro.kensait.jdbc.company;

import static pro.kensait.jdbc.util.DatabaseUtil.*;

import java.io.File;
import java.sql.Connection;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvDataSet;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("EmployeeDAOを対象に、テーブルの初期化だけを行うテストクラス")
public class InitDataTest_UPDATE {
    // DBUnitのリソースを格納するディレクトリ
    private static final String INIT_DATA_DIR = "src/test/resources/INIT_DATA";
    private static final String EXPECTED_DATA_DIR_4 = "src/test/resources/EXPECTED_DATA_4";

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

        // 初期データをセットアップする
        initData();

        // 初期データをさらにUPDATEする
        updateData();
    }

    /*
     * 初期データをセットアップする
     */
    private void initData() throws Exception {
        // CSVファイルから初期データを読み込む
        IDataSet dataSet = new CsvDataSet(new File(INIT_DATA_DIR));

        // データベースの対象テーブルから全件削除し、読み込んだ初期データを挿入する
        databaseTester.setDataSet(dataSet);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.onSetup();
    }

    /*
     * 初期データをさらにUPDATEする
     */
    private void updateData() throws Exception {
        // CSVファイルから初期データを読み込む
        IDataSet dataSet = new CsvDataSet(new File(EXPECTED_DATA_DIR_4));

        // データベースの対象テーブルを、読み込んだ初期データで更新する
        databaseTester.setDataSet(dataSet);
        databaseTester.setSetUpOperation(DatabaseOperation.UPDATE);
        databaseTester.onSetup();
    }

    @Test
    void test() {
    }
}