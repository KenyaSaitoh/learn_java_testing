package pro.kensait.java.shipping;

import java.util.ArrayList;
import java.util.List;

/*
 * 配送データを保存するための疑似的なデータアクセスクラス
 * 実際の業務アプリではデータベースが想定されるが、便宜上リストに保存する
 */
public class ShippingDAO {
    private static List<Shipping> shippingList = new ArrayList<>();

    public static void save(Shipping shipping) {
        shippingList.add(shipping);
    }

    public static List<Shipping> findAll() {
        return shippingList;
    }
}