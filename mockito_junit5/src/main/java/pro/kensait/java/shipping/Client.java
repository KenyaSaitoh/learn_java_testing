package pro.kensait.java.shipping;

/*
 * 顧客データ
 */
public record Client(
        Integer id, // ID
        String name, // 氏名
        String address, // 住所
        ClientType ClientType, // 顧客種別
        RegionType originRegion // 配送元地方
        ) {
}