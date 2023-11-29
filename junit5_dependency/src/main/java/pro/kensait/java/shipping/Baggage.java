package pro.kensait.java.shipping;

/*
 * 荷物データ
 */
public record Baggage (
    String name,
    BaggageType baggageType,
    boolean isFragile) {
}
