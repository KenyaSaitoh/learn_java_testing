package pro.kensait.java.shipping;

import static pro.kensait.jdbc.util.DatabaseUtil.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/*
 * 配送データを保存するためのリポジトリ
 * 実際の業務アプリではデータベースが想定されるが、便宜上リストに保存する
 */
public class ShippingDAO {
    private static String driver = getProperty("jdbc.driver");
    private static String url = getProperty("jdbc.url");
    private static String user = getProperty("jdbc.user");
    private static String password = getProperty("jdbc.password");
 
    {
        try {
            // JDBCドライバをロードする
            Class.forName(driver);
        } catch(ClassNotFoundException cnfe) {
            throw new RuntimeException(cnfe);
        }
    }

    public static void save(Shipping shipping) {
        // PreparedStatementに渡すSQL文を定義する
        String sqlStr = "INSERT INTO SHIPPING "
                + "(ORDER_DATE_TIME, CLIENT_NAME, RECEIVE_DATE, BAGGAGE_COUNT, TOTAL_PRICE) "
                + "VALUES(?, ?, ?, ?, ?)";
        try (
                // データベースに接続し、Connectionを取得する
                Connection conn = DriverManager.getConnection(url, user, password);
                // PreparedStatementを生成する
                PreparedStatement pstmt = conn.prepareStatement(sqlStr)) {

            // パラメータをセットする
            pstmt.setTimestamp(1, Timestamp.valueOf(shipping.orderDateTime()));
            pstmt.setString(2, shipping.client().name());
            pstmt.setDate(3, Date.valueOf(shipping.receiveDate()));
            pstmt.setInt(4, shipping.baggageList().size());
            pstmt.setInt(5, shipping.totalPrice());

            // 更新を実行する
            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
}