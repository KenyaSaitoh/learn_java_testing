package pro.kensait.java.jdbc.lob;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import pro.kensait.java.jdbc.util.PropertyUtil;

public class ObjectReadMain {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        // プロパティファイルよりデータベース情報を取得する
        String driver = PropertyUtil.getValue("jdbc.driver");
        String url = PropertyUtil.getValue("jdbc.url");
        String user = PropertyUtil.getValue("jdbc.user");
        String password = PropertyUtil.getValue("jdbc.password");

        try {
            // JDBCドライバをロードする
            Class.forName(driver);
        } catch (ClassNotFoundException cnfe) {
            throw new RuntimeException(cnfe);
        }

        String sqlStr = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = ?";

        try (// データベースに接続し、コネクションを取得する
                Connection conn = DriverManager.getConnection(url, user, password);
                // プリペアードステートメントを生成する
                PreparedStatement pstmt = conn.prepareStatement(sqlStr);
                ) {
            
            // BLOB型カラムからバイナリデータを読み出すためのストリームを取得する
            pstmt.setInt(1, 10001);
            ResultSet rset = pstmt.executeQuery();
            rset.next();
            InputStream is = rset.getBinaryStream("PHOTO");

            // ストリームからオブジェクト（List）を取得する
            List<String> list = null;
            try (ObjectInputStream ois = new ObjectInputStream(is)) {
                list = (List<String>)ois.readObject();
            } catch(IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            // 取得したListを表示する
            System.out.println("===== DBから取得したList =====");
            System.out.println(list.toString());

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
}