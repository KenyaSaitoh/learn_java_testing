package pro.kensait.junit5.shipping;

/*
 * 荷物データ
 */
public record Baggage (
    BaggageType baggageType,
    boolean isFragile) {
}
