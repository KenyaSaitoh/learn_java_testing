package pro.kensait.java.shipping;

/*
 * 荷物を表すレコードクラス
 */
public record Baggage (
    BaggageType baggageType,
    boolean isFragile) {
}
