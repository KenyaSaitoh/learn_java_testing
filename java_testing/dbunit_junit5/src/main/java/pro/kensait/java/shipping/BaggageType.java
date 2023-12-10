package pro.kensait.java.shipping;

/*
 * 荷物種別
 */
public enum BaggageType {
    SMALL(1.0F), // 小サイズ
    MIDDLE(1.5F), // 中サイズ
    LARGE(2.0F); // 大サイズ

    private final float weighting;

    private BaggageType(float weighting) {
        this.weighting = weighting;
    }

    public float getWeighting() {
        return weighting;
    }
}