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
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.DefaultTable;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.csv.CsvDataSet;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/*
 * ShippingServiceを対象にしたテストクラス
 * データベースアクセスあり
 */
public class ShippingServiceDBTest {
    // DBUnitが使用するデータ格納ディレクトリ
    private static final String EXPECTED_DATA_DIR = "src/test/resources/EXPECTED_DATA";

    // テスト対象クラス
    ShippingService shippingService;

    // テスト対象クラスの呼び出し先（モック化対象）
    @Mock
    CostCalculatorIF costCalculator;

    // テスト対象クラスの引数（モック化対象）
    @Mock
    Baggage baggage;

    // DBUnitのためのフィクスチャ
    IDatabaseTester databaseTester;
    IDatabaseConnection databaseConnection;

    // その他の各テストメソッドで共通的なテストフィクスチャ
    LocalDateTime orderDateTime;
    LocalDate receiveDate;

    // 各テストメソッドで共通的な前処理
    @BeforeEach
    void setUp() {
        // モックを初期化する（@Mockが付与されたフィールドをモック化する）
        MockitoAnnotations.openMocks(this);

        // モック化されたCostCalculatorの振る舞いを決める
        when(costCalculator.calcShippingCost(
                nullable(BaggageType.class), nullable(RegionType.class))).thenReturn(1600);

        // モックをテスト対象クラスに注入する
        shippingService = new ShippingService(costCalculator);

        // モック化されたBaggageの振る舞いを決める
        when(baggage.baggageType()).thenReturn(BaggageType.MIDDLE);

        // その他の共通的なフィクスチャを設定する
        orderDateTime = LocalDateTime.now();
        receiveDate = LocalDate.of(2023, 11, 30);
    }

    /*
     * DBUnitのテストフィクスチャやデータベース上のデータを初期化する
     */
    @BeforeEach
    void setUpDatabase() throws Exception {
        // プロパティファイルよりデータベース情報を取得する
        String driver = getProperty("jdbc.driver");
        String url = getProperty("jdbc.url");
        String user = getProperty("jdbc.user");
        String password = getProperty("jdbc.password");

        // IDatabaseTesterを初期化する
        databaseTester = new JdbcDatabaseTester(driver, url, user, password);

        // DatabaseConnectionを取得する
        databaseConnection = databaseTester.getConnection();

        // MariaDB（MySQL）用のDataTypeFactoryを設定する
        DatabaseConfig config = databaseConnection.getConfig();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());

        // 初期データをセットアップする
        initData();
    }

    /*
     * 初期データをセットアップする
     */
    private void initData() throws Exception {
        // 空のデータセットを生成する
        IDataSet emptyDataSet = new DefaultDataSet(new DefaultTable("SHIPPING"));

        // データベースの対象テーブルを初期化（全件削除）する
        databaseTester.setDataSet(emptyDataSet);
        databaseTester.setSetUpOperation(DatabaseOperation.TRUNCATE_TABLE);
        databaseTester.onSetup();
    }

    @Nested
    @DisplayName("ゴールド会員のテスト")
    class GoldCustomerTest {
        // GoldCustomerTestクラス内の各テストケースで共通的なフィクスチャ
        // テスト対象クラスの引数（モック化対象）
        @Mock
        Client client;

        // DiamondCustomerTestクラス内の各テストメソッドで共通的な前処理
        @BeforeEach
        void setUp() {
            // モックを初期化する（@Mockが付与されたフィールドをモック化する）
            MockitoAnnotations.openMocks(this);

            // モック化されたClientの振る舞いを決める（ID:30001のゴールド会員とする）
            when(client.id()).thenReturn(30001);
            when(client.clientType()).thenReturn(ClientType.GOLD);
        }

        @Test
        @DisplayName("割引なしの場合の更新をテストする")
        void test_OrderShipping_NoDiscount() throws Exception {
            // モック化されたCostCalculatorの振る舞いを決める
            when(costCalculator.calcShippingCost(
                    any(BaggageType.class), any(RegionType.class))).thenReturn(1600);

            // 引数である荷物リストを生成する（テストメソッド毎に個数が異なる）
            List<Baggage> baggageList = Arrays.asList(baggage);

            // テストを実行する
            shippingService.orderShipping(client, receiveDate, baggageList);

            // ORDER_DATE_TIME列を検証の対象外にするために、配列を用意する
            String[] excludedColumns = new String[]{"ORDER_DATE_TIME"};

            // DBUnitのAPIで、期待値テーブルをCSVファイルから取得する（ORDER_DATE_TIME列除く）
            IDataSet expectedDataSet = new CsvDataSet(new File(EXPECTED_DATA_DIR));
            ITable expectedTable = DefaultColumnFilter.excludedColumnsTable(
                    expectedDataSet.getTable("SHIPPING"), excludedColumns);

            // DBUnitのAPIで、実測値テーブルをデータベースから取得する（ORDER_DATE_TIME列除く）
            IDataSet databaseDataSet = databaseConnection.createDataSet();
            ITable actualTable = DefaultColumnFilter.excludedColumnsTable(
                    databaseDataSet.getTable("SHIPPING"), excludedColumns);

            // DBUnitのAPIで、期待値テーブルと実測値テーブルが一致しているかを検証する
            assertEquals(expectedTable, actualTable);
        }
    }
}