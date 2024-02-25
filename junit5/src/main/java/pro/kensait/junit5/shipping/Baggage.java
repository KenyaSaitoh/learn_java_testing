package pro.kensait.junit5.shipping;

/*
 * 荷物を表すレコードクラス
 */
public record Baggage (
    BaggageType baggageType,
    boolean isFragile) {
}
