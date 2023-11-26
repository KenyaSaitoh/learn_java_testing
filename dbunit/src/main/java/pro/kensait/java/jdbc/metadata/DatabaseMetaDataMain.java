package pro.kensait.java.jdbc.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import pro.kensait.java.jdbc.util.PropertyUtil;

public class DatabaseMetaDataMain {

    public static void main(String[] args) {
        // プロパティファイルよりデータベース情報を取得する
        String driver = PropertyUtil.getValue("jdbc.driver");
        String url = PropertyUtil.getValue("jdbc.url");
        String user = PropertyUtil.getValue("jdbc.user");
        String password = PropertyUtil.getValue("jdbc.password");

        try {
            // JDBCドライバをロードする
            Class.forName(driver);
        } catch(ClassNotFoundException cnfe) {
            throw new RuntimeException(cnfe);
        }

        try (
                // データベースに接続し、コネクションを取得する
                Connection conn = DriverManager.getConnection(url, user, password);
                // ステートメントを生成する
                Statement stmt = conn.createStatement();
                    ) {

            // データベースメタデータを取得する
            DatabaseMetaData dmd = conn.getMetaData();

            // JDBCドライバに関する情報を表示する
            String driverName = dmd.getDriverName();
            String driverVersion = dmd.getDriverVersion();
            System.out.println("JDBC Driver Name => " + driverName);
            System.out.println("JDBC Driver Version => " + driverVersion);

            // データベースに関する情報を表示する
            String dbName = dmd.getDatabaseProductName();
            String dbVersion = dmd.getDatabaseProductVersion();
            int dbTran = dmd.getDefaultTransactionIsolation();
            System.out.println("Database Name => " + dbName);
            System.out.println("Database Version => " + dbVersion);
            System.out.println("Database Transaction Isolation => " + dbTran);

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
}