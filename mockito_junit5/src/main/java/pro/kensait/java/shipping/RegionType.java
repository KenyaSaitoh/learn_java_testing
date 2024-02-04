package pro.kensait.java.shipping;

/*
 * 地域種別
 */
public enum RegionType {
    HOKKAIDO(2.0F),
    HONSHU(1.0F),
    SHIKOKU(1.5F),
    KYUSHU(2.0F),
    OKINAWA(2.5F);

    private final float weighting; // 重み付け

    private RegionType(float weighting) {
        this.weighting = weighting;
    }

    public float getWeighting() {
        return weighting;
    }
}