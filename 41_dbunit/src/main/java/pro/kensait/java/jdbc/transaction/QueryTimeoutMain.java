package pro.kensait.java.jdbc.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import pro.kensait.java.jdbc.util.PropertyUtil;

public class QueryTimeoutMain {

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

            // クエリタイムアウト値を設定する
            // ※意図的に別の方法でロックをかけておくこと
            stmt.setQueryTimeout(10);

            // 更新を実行する
            System.out.println("===== UPDATE文実行 =====");
            String sqlStr = "UPDATE EMPLOYEE SET SALARY = SALARY + 1 " +
                    "WHERE EMPLOYEE_ID = 10002";
            int updateCount = stmt.executeUpdate(sqlStr);
            if (0 < updateCount) {
                System.out.println(updateCount + "行更新されました");
            } else {
                System.out.println("更新された行はありません");
            }

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
}