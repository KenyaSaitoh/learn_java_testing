package pro.kensait.java.jdbc.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pro.kensait.java.jdbc.util.PropertyUtil;

public class BatchSqlMain {

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
                // データベースに接続し、別コネクションを取得する
                Connection conn2 = DriverManager.getConnection(url, user, password);
                // ステートメントを生成する
                Statement stmt = conn1.createStatement();
                ) {

            // トランザクションを開始する
            conn1.setAutoCommit(false);

            // バッチ処理実行前の情報を表示する
            System.out.println("===== バッチ処理開始前 =====");
            showEmployeeTable(conn2);

            // バッチ処理を追加する
            stmt.addBatch("DELETE FROM EMPLOYEE");
            stmt.addBatch("INSERT INTO EMPLOYEE VALUES(10001, 'Alice', '営業部', "
                    + "'2012-04-01', 'MANAGER', 500000, null)");
            stmt.addBatch("INSERT INTO EMPLOYEE VALUES(10002, 'Bob', '企画部', "
                    + "'2012-04-01', 'MANAGER', 450000, null)");
            stmt.addBatch("INSERT INTO EMPLOYEE VALUES(10003, 'Carol', '人事部', "
                    + "'2012-04-01', 'CHIEF', 350000, null)");

            // バッチ処理を実行する
            System.out.println("===== バッチ処理実行 =====");
            stmt.executeBatch();
            stmt.clearBatch();

            // トランザクションをコミットする
            conn1.commit();
            System.out.println("===== COMMIT =====");

            // バッチ処理実行後の情報を表示する
            System.out.println("===== バッチ処理終了後 =====");
            showEmployeeTable(conn2);

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }

    // トランザクションとは別コネクションでテーブルを参照する
    private static void showEmployeeTable(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            String sqlStr = "SELECT * FROM EMPLOYEE";
            ResultSet rset = stmt.executeQuery(sqlStr);
            while (rset.next()) {
                System.out.println(
                        rset.getInt("EMPLOYEE_ID") + ", " +
                        rset.getString("EMPLOYEE_NAME") + ", " +
                        rset.getString("JOB_NAME") + ", " +
                        rset.getInt("SALARY"));
            }
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
}