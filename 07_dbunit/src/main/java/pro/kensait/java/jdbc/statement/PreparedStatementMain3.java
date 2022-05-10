package pro.kensait.java.jdbc.statement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import pro.kensait.java.jdbc.util.PropertyUtil;

public class PreparedStatementMain3 {

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

        String sqlStr = "INSERT INTO EMPLOYEE VALUES (?, ?, ?, ?, ?, ?)";
        
        try (
                // データベースに接続し、コネクションを取得する
                Connection conn = DriverManager.getConnection(url, user, password);
                // プリペアードステートメントを生成する
                PreparedStatement pstmt = conn.prepareStatement(sqlStr);
                ) {

            // パラメータをセットする
            pstmt.setInt(1, 10015);
            pstmt.setString(2, "Steve");
            pstmt.setString(3, "企画部");
            Date date = Date.valueOf(LocalDate.of(2017, 10, 01));
            pstmt.setDate(4, date);
            pstmt.setString(5, "LEADER");
            pstmt.setInt(6, 380000);

            // 更新を実行する
            pstmt.executeUpdate();

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
}