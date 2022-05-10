package pro.kensait.java.jdbc.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import pro.kensait.java.jdbc.util.PropertyUtil;

public class PreparedStatementMain2 {

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

        String sqlStr = "UPDATE EMPLOYEE SET SALARY = SALARY + ? " +
                "WHERE EMPLOYEE_ID = ?";
        
        try (
                // データベースに接続し、コネクションを取得する
                Connection conn = DriverManager.getConnection(url, user, password);
                // プリペアードステートメントを生成する
                PreparedStatement pstmt = conn.prepareStatement(sqlStr);
                ) {

            // パラメータをセットする
            pstmt.setInt(1, 3000);
            pstmt.setInt(2, 10002);

            // 更新を実行する
            int updateCount = pstmt.executeUpdate();

            // 更新結果を表示する
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