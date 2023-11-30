package pro.kensait.java.shipping;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/*
 * ShippingServiceを対象にしたテストクラス
 */
public class ShippingServiceTest {
    // すべてのテストメソッド共通の変数はここに宣言する
    @Mock ShippingService shippingService;
    @Mock CostCalculatorIF costCalculator;
    @Mock Client client;
    LocalDate receiveDate;

    // 変数を初期化する
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        shippingService = new ShippingService(costCalculator);

        receiveDate = LocalDate.of(2023, 11, 30);

        // リポジトリをクリアする → これはデータベース初期化に相当する
        ShippingRepository.shippingList.clear();
    }

    @Test
    void testOrderShipping_DiamondCustomer_NoDiscount() {
        when(client.ClientType()).thenReturn(ClientType.DIAMOND);
        when(client.originRegion()).thenReturn(RegionType.KANSAI);
        when(costCalculator.calcShippingCost(
                BaggageType.MIDDLE, RegionType.KANSAI)).thenReturn(1600);

        List<Baggage> baggageList = Arrays.asList(new Baggage(BaggageType.MIDDLE, false));

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