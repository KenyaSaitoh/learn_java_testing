package pro.kensait.spring.calc.web;

import java.math.BigDecimal;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/*
 * 計算処理のパラメータ
 */
public record CalcParam(
        // パラメータ1
        @NotNull
        @Min(-1000)
        @Max(1000)
        BigDecimal param1,

        // パラメータ2
        @NotNull
        @Min(-1000)
        @Max(1000)
        BigDecimal param2
        ) {
}