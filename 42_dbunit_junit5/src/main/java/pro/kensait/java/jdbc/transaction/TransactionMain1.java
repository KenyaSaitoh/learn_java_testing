package pro.kensait.java.jdbc.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pro.kensait.java.jdbc.util.PropertyUtil;

public class TransactionMain1 {

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

            // トランザクションのアイソレーションレベルを設定する
            conn1.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            // 更新前の情報を表示する
            System.out.println("===== BEFORE TRANSACTION =====");
            showEmployeeTable(conn2);

            // 新規ローを挿入する
            stmt.executeUpdate("INSERT INTO EMPLOYEE VALUES(10015, 'Steve', '企画部',"
                    + " '2017-10-01', 'LEADER', 380000, null)");
            System.out.println("===== INSERT FINISH BUT COMMIT NOT FINISH =====");
            showEmployeeTable(conn2);

            // 意図的にトランザクションをロールバックする
            conn1.rollback();
            System.out.println("===== ROLLBACK =====");
            showEmployeeTable(conn2);

            // 再度新規ローを挿入する
            stmt.executeUpdate("INSERT INTO EMPLOYEE VALUES(10015, 'Steve', '企画部',"
                    + " '2017-10-01', 'LEADER', 380000, null)");
            System.out.println("===== INSERT FINISH BUT COMMIT NOT FINISH =====");
            showEmployeeTable(conn2);

            // トランザクションをコミットする
            conn1.commit();
            System.out.println("===== COMMIT =====");
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