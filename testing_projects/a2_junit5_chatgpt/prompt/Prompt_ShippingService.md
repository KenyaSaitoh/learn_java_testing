以下の［制約条件］に従い、単体テストを行います。
テストコードを、生成してください。

# 制約条件

* テスティングフレームワークには、JUnit5を利用してください。
* モッキングフレームワークには、Mockitoを利用してください。
* テスト対象クラスは`pro.kensait.java.shipping.ShippingService`です。
* テストクラスは`pro.kensait.java.shipping.ShippingServiceTest`とします。
* @BeforeEachが付与された前処理において、ShippingServiceクラスのインスタンスを生成し、
  shippingServiceフィールドに割り当てます。
* 以下のクラスをモック化し、カバレッジを意識した振る舞いを実装してください。
  * Baggage
  * Client
  * CostCalculatorIF
* 検証は開発者自身で行うため、コードの生成は不要です。
* テストメソッド名は英語で、コメントは日本語でお願いします。
* すべてのテストメソッドを生成してください。

# テスト対象クラス
package pro.kensait.java.shipping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/*
 * 配送サービス本体を表すクラス（テスト対象）
 */
public class ShippingService {
    private static final float GOLD_NET_RATE = 0.9F; // ゴールド会員の割引率
    private static final int GOLD_COST_LIMIT = 3000; // ゴールド会員の割引後の下限金額
    private static final float DIAMOND_NET_RATE = 0.75F; // ダイヤモンド会員の割引率
    private static final int DIAMOND_COST_LIMIT = 2500; // ダイヤモンド会員の割引後の下限金額

    // 荷物毎の配送料計算ロジックを表すインタフェース
    private CostCalculatorIF costCalculator;

    // コンストラクタ
    public ShippingService(CostCalculatorIF costCalculator) {
        this.costCalculator = costCalculator;
    }

    // 配送の注文を受ける
    public void orderShipping(Client client, LocalDate receiveDate, List<Baggage> baggageList) {

        // 配送料の合計値
        Integer totalCost = 0;

        // 荷物リストでループし、一つ一つの荷物種別を表す列挙型ごとに配送料を計算
        // → それらを集計し、配送料の合計値を算出する
        for (Baggage baggage : baggageList) {
            Integer shippingCost =
                costCalculator.calcShippingCost(baggage.baggageType(),
                client.originRegion());
            totalCost = totalCost + shippingCost;
        }

        // ゴールド会員の場合は、ゴールド会員用の割引率を適用する
        // → ただし定義された「割引後の下限金額」を下回ることは許容されない
        if (client.clientType() == ClientType.GOLD) {
            if (GOLD_COST_LIMIT < totalCost) {
                Integer discountedPrice = Integer.class.cast(
                        Math.round(totalCost * GOLD_NET_RATE));
                totalCost = discountedPrice < GOLD_COST_LIMIT ?
                        GOLD_COST_LIMIT :
                            discountedPrice;
            }

        // ダイヤモンド会員の場合は、ダイヤモンド会員用の割引率を適用する
        // → ただし定義された「割引後の下限金額」を下回ることは許容されない
        } else if (client.clientType() == ClientType.DIAMOND) {
            if (DIAMOND_COST_LIMIT < totalCost) {
                Integer discountedPrice = Integer.class.cast(
                        Math.round(totalCost * DIAMOND_NET_RATE));
                totalCost = discountedPrice < DIAMOND_COST_LIMIT ?
                        DIAMOND_COST_LIMIT :
                            discountedPrice;
            }
        }

        // 配送データを表すレコードを生成する
        Shipping shipping = new Shipping(LocalDateTime.now(), client, receiveDate, baggageList,
                totalCost);

        // 配送DAOに配送レコードを保存する
        ShippingDAO.save(shipping);
    }
}

# 依存クラス
/*
 * 荷物毎の配送料計算ロジックを表すインタフェース
 */
public interface CostCalculatorIF {
    // 配送料計算のベースとなる価格
    public static final Integer BASE_PRICE = 1000;

    // 配送料金を計算するメソッド
    Integer calcShippingCost(BaggageType baggageType, RegionType regionType);
}

/*
 * 荷物を表すレコードクラス
 */
public record Baggage (
    BaggageType baggageType,
    boolean isFragile) {
}

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

/*
 * 顧客を表すレコードクラス
 */
public record Client(
        Integer id, // ID
        String name, // 氏名
        String address, // 住所
        ClientType clientType, // 顧客種別
        RegionType originRegion // 配送元地方
        ) {
}

/*
 * 顧客種別を表す列挙型
 */
public enum ClientType {
    GENERAL, GOLD, DIAMOND;
}

/*
 * 発送元の地域種別を表す列挙型
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

package pro.kensait.java.shipping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/*
 * 配送データを表すレコードクラス
 */
public record Shipping(
        LocalDateTime orderDateTime, // 注文日時
        Client client, // 顧客
        LocalDate receiveDate, // 受取日
        List<Baggage> baggageList, // 荷物リスト
        Integer totalPrice) // 合計金額 
{
    @Override
    public int hashCode() {
        return Objects.hash(baggageList, client, receiveDate, totalPrice);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Shipping other = (Shipping) obj;
        return Objects.equals(baggageList, other.baggageList)
                && Objects.equals(client, other.client)
                && Objects.equals(receiveDate, other.receiveDate)
                && Objects.equals(totalPrice, other.totalPrice);
    }
}
```