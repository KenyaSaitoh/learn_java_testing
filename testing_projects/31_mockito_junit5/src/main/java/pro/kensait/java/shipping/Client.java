package pro.kensait.java.shipping;

/*
 * 顧客を表すレコードクラス
 */
public record Client(
        Integer id, // ID
        String name, // 氏名
        String address, // 住所
        ClientType clientType, // 顧客種別
        RegionType originRegion // 配送元地方
        ) {
}