package org.study.junit.dbunit.cleanup;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.BeforeClass;

public class TestBase {

    private static final String RESOURCE_BASE_DIR = "app/src/test/resources/";

    // テストを通して一回だけ事前実行
    @BeforeClass
    public static void initialize() throws Exception {
        IDataSet tables = new CsvDataSet(
                new File(RESOURCE_BASE_DIR + "/TABLE_INIT_DATA"));

        // テストデータをセットアップする（全件削除する）。
        DatabaseOperation.CLEAN_INSERT.execute(getConnection(), tables);
    }

    protected static IDatabaseConnection getConnection() {
        String driver = PropertyUtil.getValue("jdbc.driver");
        String url = PropertyUtil.getValue("jdbc.url");
        String user = PropertyUtil.getValue("jdbc.user");
        String password = PropertyUtil.getValue("jdbc.password");
        DatabaseConnection conn = null;
        try {
            Class.forName(driver);
            Connection jdbcConn = DriverManager.getConnection(url, user,
                    password);
            // DatabaseConnectionを生成する際にユーザ名を再度設定しないと、
            // すべてのスキーマをマッチング対象にしてしまう。
            String databaseName = jdbcConn.getMetaData()
                    .getDatabaseProductName();
            if (databaseName.startsWith("MySQL")) {
                // MySQLの場合は引数にスキーマを指定すると、DB名と見なされてしまい正しくアクセスできないため。
                conn = new DatabaseConnection(jdbcConn);
            } else {
                // MySQL以外の場合は、スキーマ名を明示する。
                conn = new DatabaseConnection(jdbcConn, user);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
