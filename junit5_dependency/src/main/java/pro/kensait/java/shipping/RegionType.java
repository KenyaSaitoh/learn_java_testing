package pro.kensait.java.shipping;

/*
 * 地域種別
 */
public enum RegionType {
    HOKKAIDO(1.5F),
    HONSHU(1.0F),
    SHIKOKU(1.5F),
    KYUSHU(1.5F),
    OKINAWA(2.0F);

    private final float weighting; // 重み付け

    private RegionType(float weighting) {
        this.weighting = weighting;
    }

    public float getWeighting() {
        return weighting;
    }
}