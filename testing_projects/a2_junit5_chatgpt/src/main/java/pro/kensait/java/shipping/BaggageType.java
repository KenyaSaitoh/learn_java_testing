package pro.kensait.java.shipping;

/*
 * 荷物種別を表す列挙型
 */
public enum BaggageType {
    SMALL(1.0F), // 小サイズ
    MIDDLE(1.2F), // 中サイズ
    LARGE(1.5F); // 大サイズ

    private final float weighting;

    private BaggageType(float weighting) {
        this.weighting = weighting;
    }

    public float getWeighting() {
        return weighting;
    }
}