package pro.kensait.java.timeofday;

/*
 * 時間管理のためのビジネスロジックを表すインタフェース（モック対象）
 */
public interface TimeManagerIF {
    TimeOfDay getCurrent();
}