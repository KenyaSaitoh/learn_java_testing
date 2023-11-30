package pro.kensait.java.shipping;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/*
 * ShippingServiceを対象にしたテストクラス
 */
public class ShippingServiceTest {
    /*
     *  すべてのテストメソッドに共通的な変数はフィールドとして宣言する
     */

    // テスト対象クラス
    ShippingService shippingService;

    // 共通的な変数
    LocalDate receiveDate;

    // 共通的な変数のうち、モック対象（@Mockを付与）
    @Mock CostCalculatorIF costCalculator;
    @Mock Client client; // ???

    // 変数を初期化する
    @BeforeEach
    void setUp() {
        // モックを初期化する（@Mockが付与されたフィールドにモックを割り当てる）
        MockitoAnnotations.openMocks(this);


        shippingService = new ShippingService(costCalculator);
        receiveDate = LocalDate.of(2023, 11, 30);

        // リポジトリをクリアする → これはデータベース初期化に相当する
        ShippingRepository.shippingList.clear();
    }

    @Test
    void testOrderShipping_DiamondCustomer_NoDiscount() {
        // モック化されたcostCalculatorの振る舞いを決める
        when(costCalculator.calcShippingCost(
                BaggageType.MIDDLE, RegionType.KANSAI)).thenReturn(1600);

        // モック化されたclientの振る舞いを決める
        // 引数なのでモックか不要かも！
        when(client.ClientType()).thenReturn(ClientType.DIAMOND);
        when(client.originRegion()).thenReturn(RegionType.KANSAI);

        List<Baggage> baggageList = List.of(new Baggage(BaggageType.MIDDLE, false));

        // テスト実行
        shippingService.orderShipping(client, receiveDate, baggageList);

        // リポジトリから「実際の値」を取得する
        Shipping actual = ShippingRepository.shippingList.get(0);

        // 「期待値」を生成する
        Shipping expected = new Shipping(LocalDate.now(), client, receiveDate, baggageList,
                1600);

        // 「期待値」と「実際の値」が一致しているかを検証する
        assertEquals(expected, actual);
    }
}