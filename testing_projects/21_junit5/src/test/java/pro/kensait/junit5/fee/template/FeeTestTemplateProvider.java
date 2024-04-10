package pro.kensait.junit5.fee.template;

// JUnit 5拡張機能をインポート
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;
import org.junit.jupiter.params.provider.Arguments;

/*
 * テストテンプレートのプロバイダ
 * TestTemplateInvocationContextProviderインターフェースを実装するクラスを定義
 */
public class FeeTestTemplateProvider implements TestTemplateInvocationContextProvider {
    private static final String OUR_BANK_CODE = "B001";
    private static final String OTHER_BANK_CODE = "B999";

    // 手数料計算のテストケースを提供するスタティックメソッド
    static Stream<Arguments> feeCalculationArguments() {
        // 銀行コード、振込金額、期待される手数料の組み合わせを提供
        return Stream.of(
                Arguments.of(OUR_BANK_CODE, 30000, 0),
                Arguments.of(OUR_BANK_CODE, 29999, 100),
                Arguments.of(OTHER_BANK_CODE, 40000, 200),
                Arguments.of(OTHER_BANK_CODE, 39999, 500));
    }

    @Override
    public boolean supportsTestTemplate(ExtensionContext context) {
        // このプロバイダがすべてのテストテンプレートに対応することを示す
        return true;
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(
            ExtensionContext context) {
        // feeCalculationArgumentsメソッドから提供されたテストデータを基にテストテンプレートのコンテキストを生成
        return feeCalculationArguments().map(arguments -> new TestTemplateInvocationContext() {
            @Override
            public String getDisplayName(int invocationIndex) {
                // テスト実行時の表示名
                return String.format("［ 銀行コード = %s, 金額 = %d, 期待手数料 = %d ]",
                        arguments.get()[0], arguments.get()[1], arguments.get()[2]);
            }

            @Override
            public List<Extension> getAdditionalExtensions() {
                // パラメータリゾルバを追加して、テストメソッドに渡されるパラメータを解決
                return List.of(new ParameterResolver() {
                    @Override
                    public boolean supportsParameter(ParameterContext parameterContext,
                            ExtensionContext extensionContext) {
                        // このリゾルバがすべてのパラメータをサポートすることを示す
                        return true;
                    }

                    @Override
                    public Object resolveParameter(ParameterContext parameterContext,
                            ExtensionContext extensionContext) {
                        // テストメソッドに渡されるべきパラメータの値を解決して提供
                        return arguments.get()[parameterContext.getIndex()];
                    }
                });
            }
        });
    }
}