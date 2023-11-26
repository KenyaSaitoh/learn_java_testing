package pro.kensait.java.jdbc.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import pro.kensait.java.jdbc.util.PropertyUtil;

public class LockMain {

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
                // データベースに接続し、トランザクション用コネクションを取得する
                Connection conn1 = DriverManager.getConnection(url, user, password);
                // ステートメントを生成する
                Statement stmt = conn1.createStatement();
                ) {

            // トランザクションを開始する
            conn1.setAutoCommit(false);

            // SELECT FOR UPDATE文を実行する
            System.out.println("===== SELECT FOR UPDATE文実行 =====");
            stmt.executeQuery("SELECT * FROM EMPLOYEE "
                    + "WHERE EMPLOYEE_ID = '10002' FOR UPDATE ");

            // 無限ループする
            while(true) {}

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
}