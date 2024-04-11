package pro.kensait.spring.calc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config.properties")
public class ConfigHolder {
    // 計算結果の小数点以下桁数
    @Value("${calc.result.scale}")
    private Integer calcResultScale;
 
    // 計算結果の極度
    @Value("${calc.result.limit}")
    private Integer calcResultLimit;

    public Integer getCalcResultScale() {
        return calcResultScale;
    }

    public void setCalcResultScale(Integer calcResultScale) {
        this.calcResultScale = calcResultScale;
    }

    public Integer getCalcResultLimit() {
        return calcResultLimit;
    }

    public void setCalcResultLimit(Integer calcResultLimit) {
        this.calcResultLimit = calcResultLimit;
    }
}
