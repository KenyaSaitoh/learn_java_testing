package pro.kensait.java.shipping;

/*
 * 地域種別
 */
public enum RegionType {
    HOKKAIDO(1.5F),
    HONSHU(1.0F),
    SHIKOKU(1.3F),
    KYUSHU(1.3F),
    OKINAWA(1.8F);

    private final float weighting; // 重み付け

    private RegionType(float weighting) {
        this.weighting = weighting;
    }

    public float getWeighting() {
        return weighting;
    }
}