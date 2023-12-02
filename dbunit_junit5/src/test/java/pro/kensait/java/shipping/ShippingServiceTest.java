package pro.kensait.java.shipping;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static pro.kensait.jdbc.util.DatabaseUtil.*;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/*
 * ShippingServiceを対象にしたテストクラス
 */
public class ShippingServiceTest {
    private static final String TEST_DATA_FILE = "src/test/resources/test-data.cav";
    private static final String EXPECTED_DATA_FILE_1 = "src/test/resources/expected-data-1.csv";

    /*
     *  すべてのテストメソッドに共通的な変数はフィールドとして宣言する
     */

    // テスト対象クラス
    ShippingService shippingService;

    // テスト対象クラスが依存しているクラス（@Mockを付与してモック化）
    @Mock CostCalculatorIF costCalculator;

    // 上記以外の共通的な変数
    Client goldClient;
    Client diamondClient;
    Baggage baggage;
    LocalDateTime orderDateTime;
    LocalDate receiveDate;

    // DBユニットのための変数
    IDatabaseTester databaseTester;

    /*
     *  各テストメソッド呼び出しの前処理（共通変数の初期化など）
     */
    @BeforeEach
    void setUp() {
        // モックを初期化する（@Mockが付与されたフィールドにモックを割り当てる）
        MockitoAnnotations.openMocks(this);

        // モックをテスト対象クラスに注入する
        shippingService = new ShippingService(costCalculator);

        // 変数を設定する
        goldClient = new Client(10001, "Alice", "大阪府住吉区1-1-1",
                ClientType.GOLD, RegionType.KANSAI);
        diamondClient = new Client(10001, "Alice", "大阪府住吉区1-1-1",
                ClientType.DIAMOND, RegionType.KANSAI);
        baggage = new Baggage(BaggageType.MIDDLE, false);
        orderDateTime = LocalDateTime.now();
        receiveDate = LocalDate.of(2023, 11, 30);

        // DAOが保持するリストをクリアする（DB利用時はテーブル初期化に相当する）
    }

    @BeforeEach
    public void setUpDatabase() throws Exception {
        // プロパティファイルよりデータベース情報を取得する
        String driver = getProperty("jdbc.driver");
        String url = getProperty("jdbc.url");
        String user = getProperty("jdbc.user");
        String password = getProperty("jdbc.password");

        // IDatabaseTesterを初期化する
        databaseTester = new JdbcDatabaseTester(driver, url, user, password);

        // CSVファイルから初期データを読み込む
        IDataSet dataSet = new CsvDataSet(new File(TEST_DATA_FILE));
        databaseTester.setDataSet(dataSet);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.onSetup();
    }

    /*
     * ダイヤモンド会員で、割引になった場合（下限に到達せず）の更新結果をテストする
     */
    @Test
    void testOrderShipping_DiamondCustomer_Discount_NoLimit() {
        // モック化されたCostCalculatorの振る舞いを決める
        when(costCalculator.calcShippingCost(
                any(BaggageType.class), any(RegionType.class))).thenReturn(1600);

        // 引数である荷物リストを生成する（テスト毎に個数が異なる）
        List<Baggage> baggageList = Arrays.asList(baggage, baggage, baggage);

        // テスト実行
        shippingService.orderShipping(diamondClient, receiveDate, baggageList);

        // DAOが保持するリストから「実際の値」を取得する

        // 「期待値」を生成する
        Shipping expected = new Shipping(orderDateTime, diamondClient, receiveDate,
                baggageList, 3600);

        // 「期待値」と「実際の値」が一致しているかを検証する
        //assertEquals(expected, actual);
    }
}