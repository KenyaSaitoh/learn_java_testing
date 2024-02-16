package pro.kensait.junit5.testing;

/*
 * 特殊な計算機
 */
public class SpecialCalculator {

    // 足し算を実行し、その答えを返すメソッド（テスト対象）
    public int add(int param1, int param2) {
        // 2つのパラメータがいずれも負の場合は、パラメータ不正エラーにする
        if (param1 < 0 && param2 < 0) {
            throw new RuntimeException("パラメータ不正エラー");
        }

        // 足し算を実行する
        int answer = param1 + param2;

        // 足し算の結果が負の場合は、結果不正エラーにする
        if (answer < 0) {
            throw new RuntimeException("結果不正エラー");
        }

        // 足し算の結果を返す
        return answer;
    }
}