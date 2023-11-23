package pro.kensait.java.shipping;

public enum BaggageType {
    SMALL(1.0F),
    MIDDLE(1.5F),
    LARGE(2.0F);

    private final float weighting;

    private BaggageType(float weighting) {
        this.weighting = weighting;
    }

    public float getWeighting() {
        return weighting;
    }
}