package pro.kensait.java.shipping;

public record ShippingClient(
        Integer id, // ID
        String name, // 氏名
        String address, // 住所
        ClientType ClientType, // 会員種別
        RegionType originRegion // 配送元地方
        ) {
}