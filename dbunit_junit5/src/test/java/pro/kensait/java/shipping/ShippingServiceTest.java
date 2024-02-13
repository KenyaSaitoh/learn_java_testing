package pro.kensait.java.shipping;

import static org.dbunit.Assertion.*; // これを先にimportすることが重要！
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
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.DefaultTable;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.csv.CsvDataSet;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/*
 * ShippingServiceを対象にしたテストクラス
 */
public class ShippingServiceTest {
    // DBUnitが使用するデータ格納ディレクトリ
    private static final String EXPECTED_DATA_DIR = "src/test/resources/EXPECTED_DATA";

    // 各テストケースで共通的なフィクスチャを、フィールドとして宣言する
    // テスト対象クラス
    ShippingService shippingService;

    // テスト対象クラスの呼び出し先（@Mockを付与してモック化）
    @Mock CostCalculatorIF costCalculator;

    // DBユニットのためのフィクスチャ
    IDatabaseTester databaseTester;
    IDatabaseConnection databaseConnection;

    // その他の各テストメソッドで共通的なフィクスチャ
    Client goldClient;
    Client diamondClient;
    Baggage baggage;
    LocalDateTime orderDateTime;
    LocalDate receiveDate;

    // 各テストケースで共通的な事前処理
    @BeforeEach
    void setUp() {
        // モックを初期化する（@Mockが付与されたフィールドにモックを割り当てる）
        MockitoAnnotations.openMocks(this);

        // モックをテスト対象クラスに注入する
        shippingService = new ShippingService(costCalculator);

        // 共通フィクスチャを設定する
        goldClient = new Client(10001, "Alice", "福岡県福岡市1-1-1",
                ClientType.GOLD, RegionType.KYUSHU);
        diamondClient = new Client(10001, "Alice", "福岡県福岡市1-1-1",
                ClientType.DIAMOND, RegionType.KYUSHU);
        baggage = new Baggage(BaggageType.MIDDLE, false);
        orderDateTime = LocalDateTime.now();
        receiveDate = LocalDate.of(2023, 11, 30);
    }

    // データベースやDBUnitを初期化する
    @BeforeEach
    void setUpDatabase() throws Exception {
        // プロパティファイルよりデータベース情報を取得する
        String driver = getProperty("jdbc.driver");
        String url = getProperty("jdbc.url");
        String user = getProperty("jdbc.user");
        String password = getProperty("jdbc.password");

        // IDatabaseTesterを初期化する
        databaseTester = new JdbcDatabaseTester(driver, url, user, password);

        // 空のデータセットを生成する
        IDataSet emptyDataSet = new DefaultDataSet(new DefaultTable("SHIPPING"));

        // テーブルを初期化（全件削除）する
        databaseTester.setDataSet(emptyDataSet);
        databaseTester.setSetUpOperation(DatabaseOperation.TRUNCATE_TABLE);
        databaseTester.onSetup();
    }

    @Test
    @DisplayName("ダイヤモンド会員で、割引になった場合（下限に到達せず）の更新結果をテストする")
    void test_OrderShipping_DiamondCustomer_Discount_NoLimit() throws Exception {
        // モック化されたCostCalculatorの振る舞いを決める
        when(costCalculator.calcShippingCost(
                any(BaggageType.class), any(RegionType.class))).thenReturn(1600);

        // 引数である荷物リストを生成する（テストメソッド毎に個数が異なる）
        List<Baggage> baggageList = Arrays.asList(baggage, baggage, baggage);

        // テスト実行
        shippingService.orderShipping(diamondClient, receiveDate, baggageList);

        // ORDER_DATE_TIME列を検証の対象外にするために、配列を用意する
        String[] excludedColumns = new String[]{"ORDER_DATE_TIME"};

        // 期待値となるテーブル（ORDER_DATE_TIME列除く）を取得する
        IDataSet expectedDataSet = new CsvDataSet(new File(EXPECTED_DATA_DIR));
        ITable expectedTable = DefaultColumnFilter.excludedColumnsTable(
                expectedDataSet.getTable("SHIPPING"), excludedColumns);

        // 実測値となるテーブル（ORDER_DATE_TIME列除く）を取得する
        IDataSet databaseDataSet = databaseTester.getConnection().createDataSet();
        ITable actualTable = DefaultColumnFilter.excludedColumnsTable(
                databaseDataSet.getTable("SHIPPING"), excludedColumns);

        // 期待値と実測値が一致しているかを検証する
        assertEquals(expectedTable, actualTable);
    }
}