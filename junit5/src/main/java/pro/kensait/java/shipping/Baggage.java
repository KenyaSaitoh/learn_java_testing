package pro.kensait.java.shipping;

/*
 * 荷物データ
 */
public record Baggage (
    BaggageType baggageType,
    boolean isFragile) {
}
