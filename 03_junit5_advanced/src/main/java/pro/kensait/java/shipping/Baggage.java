package pro.kensait.java.shipping;

public record Baggage (
    String name,
    BaggageType baggageType,
    boolean isFragile) {
}
