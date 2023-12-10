package pro.kensait.java.shipping;

/*
 * 地域種別
 */
public enum RegionType {
    HOKKAIDO(2.0F),
    TOHOKU(1.5F),
    KANTO(1.0F),
    CHUBU(1.2F),
    KANSAI(1.5F),
    CHUGOKU(1.8F),
    SHIKOKU(2.0F),
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