package pro.kensait.java.jdbc.lob;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import pro.kensait.java.jdbc.util.PropertyUtil;

public class ObjectWriteMain2 {

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

        // バイナリデータとしてListを生成する
        List<String> list = Arrays.asList("Foo", "Bar", "Baz");

        String sqlStr = "INSERT INTO EMPLOYEE VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (// データベースに接続し、コネクションを取得する
                Connection conn = DriverManager.getConnection(url, user, password);
                // プリペアードステートメントを生成する
                PreparedStatement pstmt = conn.prepareStatement(sqlStr)) {

            // ストリームにオブジェクト（List）を書き出す
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                oos.writeObject(list);
            } catch(IOException ioe) {
                throw new RuntimeException(ioe);
            }

            int len = baos.size();
            InputStream is = new ByteArrayInputStream(baos.toByteArray());

            // パラメータをセットする
            pstmt.setInt(1, 10016);
            pstmt.setString(2, "Trent");
            pstmt.setString(3, "商品開発部");
            Date date = Date.valueOf(LocalDate.of(2017, 10, 01));
            pstmt.setDate(4, date);
            pstmt.setString(5, "CHIEF");
            pstmt.setInt(6, 310000);
            pstmt.setBinaryStream(7, is, len); // BLOB型カラムにストリームをセット

            // 更新を実行する
            pstmt.executeUpdate();

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
}