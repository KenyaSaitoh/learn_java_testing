package pro.kensait.java.jdbc.exception;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import pro.kensait.java.jdbc.util.PropertyUtil;

public class SQLExceptionMain {

    public static void main(String[] args) throws Exception {
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

            // インサートを実行する（一意制約違反）
            stmt.executeUpdate("INSERT INTO EMPLOYEE VALUES(10001, 'Steve', '企画部',"
                    + " '2017-10-01', 'LEADER', 380000, null)");

        } catch (SQLException sqle) {
            while (sqle != null) {
                System.out.println("===== 発生したSQLExceptionの情報 =====");
                System.out.println("メッセージ => " + sqle.getLocalizedMessage());
                System.out.println("SQLStateコード => " + sqle.getSQLState());
                System.out.println("ベンダー固有のエラーコード => " + sqle.getErrorCode());
                sqle = sqle.getNextException();
            }
            throw new RuntimeException(sqle);
        }
    }
}