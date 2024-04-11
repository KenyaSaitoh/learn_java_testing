package pro.kensait.spring.calc.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.kensait.spring.calc.config.ConfigHolder;
import pro.kensait.spring.calc.service.exception.LimitOverException;
import pro.kensait.spring.calc.service.exception.ZeroDivideException;

/*
 * 計算機能のビジネスロジックを表すクラス
 */
@Service
public class CalcService {
    private static final Logger logger = LoggerFactory.getLogger(
            CalcService.class);

    // インジェクションポイント
    @Autowired
    private ConfigHolder config;

    // サービスメソッド：足し算を実行する
    public BigDecimal add(BigDecimal param1, BigDecimal param2) {
        logger.info("[ CalcService#add ]");

        // 足し算を実行し、四捨五入してスケールを合わせる
        BigDecimal result = param1.add(param2)
                .setScale(config.getCalcResultScale(), RoundingMode.HALF_UP);

        return result;
    }

    // サービスメソッド：引き算を実行する
    public BigDecimal subtract(BigDecimal param1, BigDecimal param2) {
        logger.info("[ CalcService#subtract ]");

        // 引き算を実行し、四捨五入してスケールを合わせる
        BigDecimal result = param1.subtract(param2)
                .setScale(config.getCalcResultScale(), RoundingMode.HALF_UP);

        return result;
    }

    // サービスメソッド：掛け算を実行する
    public BigDecimal multiply(BigDecimal param1, BigDecimal param2)
            throws LimitOverException {

        logger.info("[ CalcService#multiply ]");

        BigDecimal tmpResult = param1.multiply(param2);

        // 計算結果が極度オーバーしていないかチェックし、発生した場合は例外をスローする
        BigDecimal limit = new BigDecimal(config.getCalcResultLimit());
        if (0 < tmpResult.compareTo(limit)) {
            throw new LimitOverException();
        }

        // 掛け算の結果から、四捨五入してスケールを合わせた結果を取得する
        BigDecimal result = tmpResult.setScale(config.getCalcResultScale(),
                RoundingMode.HALF_UP);
        return result;
    }

    // サービスメソッド：割り算を実行する
    public BigDecimal divide(BigDecimal param1, BigDecimal param2)
            throws ZeroDivideException {

        logger.info("[ CalcService#divide ]");

        // ゼロ割が発生するかチェックし、発生した場合は例外をスローする
        if (param2.compareTo(BigDecimal.ZERO) == 0) {
            throw new ZeroDivideException();
        }

        // 割り算を実行し、四捨五入してスケールを合わせる
        BigDecimal result = param1.divide(param2, config.getCalcResultScale(),
                RoundingMode.HALF_UP);
        return result;
    }
}