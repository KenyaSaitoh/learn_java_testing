package pro.kensait.spring.calc.web;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pro.kensait.spring.calc.service.CalcService;
import pro.kensait.spring.calc.service.exception.LimitOverException;
import pro.kensait.spring.calc.service.exception.ZeroDivideException;

/*
 * 計算機能のコントローラーを担うクラス
 */
@Controller
public class CalcController {
    private static final Logger logger = LoggerFactory.getLogger(
            CalcController.class);

    // インジェクションポイント
    @Autowired
    private CalcService calcService;

    // インジェクションポイント
    @Autowired
    private MessageSource messageSource;

    // アクションメソッド：入力画面に遷移する
    @RequestMapping("/")
    public String index(@ModelAttribute("calcParam") CalcParam calcParam) {
        logger.info("[ CalcController#index ]");
        return "CalcInputPage";
    }

    // アクションメソッド：足し算を実行する
    @RequestMapping("/add")
    // BindingResultは、必ずパラメータの直後の引数に指定する
    public String add(@Validated CalcParam calcParam, BindingResult errors,
            Model model) {

        logger.info("[ CalcController#add ]");

        // param1の値が0であるか入力チェックを行い、エラーがあった場合はBindingResultに追加する
        // ※他のエラーメッセージと一括して表示するため、画面遷移はしない
        if (calcParam.param1() != null &&
                calcParam.param1().equals(BigDecimal.valueOf(0.0d))) {
            logger.info("[ CalcController#add ] ゼロ加算エラー");
            errors.rejectValue("param1", "error.zero.value", "パラメータ1"); // 第1引数param1はcalcParamのプロパティと一致している必要あり
        }

        // param2の値が0であるか入力チェックを行い、エラーがあった場合はBindingResultに追加する
        // ※他のエラーメッセージと一括して表示するため、画面遷移はしない
        if (calcParam.param2() != null &&
                calcParam.param2().equals(BigDecimal.valueOf(0.0d))) {
            logger.info("[ CalcController#add ] ゼロ加算エラー");
            errors.rejectValue("param2", "error.zero.value", "パラメータ2"); // 第1引数param2はcalcParamのプロパティと一致している必要あり
        }

        // 入力値検証の結果を調べ、エラー発生時は入力画面へ遷移する
        if (errors.hasErrors()) {
            return "CalcInputPage";
        }

        // ビジネスロジックを呼び出す
        BigDecimal result = calcService.add(calcParam.param1(), calcParam.param2());

        // 計算結果をModelに格納する
        model.addAttribute("result", result);

        // 遷移先ページ（結果画面）の論理名を返却する
        return "CalcOutputPage";
    }

    // アクションメソッド：引き算を実行する
    @RequestMapping("/subtract")
    public String subtract(@Validated CalcParam calcParam, BindingResult errors,
            Model model) {

        logger.info("[ CalcController#subtract ]");

        // 2つのパラメータの値が同一だった場合はエラーにする
        // → グローバルエラーを追加し、元の画面に遷移する
        if (calcParam.param1().equals(calcParam.param2())) {
            ObjectError error = new ObjectError("globarError",
                    new String[]{"error.same.value"}, null, null);
            errors.addError(error);
        }

        // 入力値検証の結果を調べ、エラー発生時は入力画面へ遷移する
        if (errors.hasErrors()) {
            return "CalcInputPage";
        }

        // ビジネスロジックを呼び出す
        BigDecimal result = calcService.subtract(calcParam.param1(), calcParam.param2());

        // 計算結果をModelに格納する
        model.addAttribute("result", result);

        // 遷移先ページ（結果画面）の論理名を返却する
        return "CalcOutputPage";
    }

    // アクションメソッド：掛け算を実行する
    @RequestMapping("/multiply")
    public String multiply(@Validated CalcParam calcParam, BindingResult errors,
            Model model) {

        logger.info("[ CalcController#multiply ]");

        // 入力値検証の結果を調べ、エラー発生時は入力画面へ遷移する
        if (errors.hasErrors()) {
            return "CalcInputPage";
        }

        // ビジネスロジックを呼び出す
        BigDecimal result = null;
        try {
            result = calcService.multiply(calcParam.param1(),
                    calcParam.param2());
        } catch(LimitOverException loe) {
            // ビジネスロジックでエラー（極度オーバー）が発生

            // エラーログを出力する
            logger.error("極度オーバーが発生しました", loe);

            // メッセージソースよりエラーメッセージを取得し、Modelに格納する
            String errorMessage = messageSource.getMessage("error.limit.over",
                    new String[] {calcParam.param1().toString(),
                            calcParam.param2().toString()},
                    null);
            model.addAttribute("errorMessage", errorMessage);

            // 遷移先ページ（エラー画面）の論理名を返却する
            return "CalcErrorPage";
        }

        // 計算結果をModelに格納する
        model.addAttribute("result", result);

        // 遷移先ページ（結果画面）の論理名を返却する
        return "CalcOutputPage";
    }

    // アクションメソッド：割り算を実行する
    @RequestMapping("/divide")
    public String divide(@Validated CalcParam calcParam, BindingResult errors,
            Model model) {

        logger.info("[ CalcController#divide ]");

        // 入力値検証の結果を調べ、エラー発生時は入力画面へ遷移する
        if (errors.hasErrors()) {
            return "CalcInputPage";
        }

        // ビジネスロジックを呼び出す
        BigDecimal result = null;
        try {
            result = calcService.divide(calcParam.param1(),
                    calcParam.param2());
        } catch(ZeroDivideException zde) {
            // ビジネスロジックでエラー（ゼロ割り）が発生

            // エラーログを出力する
            logger.error("ゼロ割が発生しました", zde);

            // メッセージソースよりエラーメッセージを取得し、Modelに格納する
            String errorMessage = messageSource.getMessage("error.zero.divide", null,
                    null);
            model.addAttribute("errorMessage", errorMessage);

            // 遷移先ページ（エラー画面）の論理名を返却する
            return "CalcErrorPage";
        }

        // 計算結果をModelに格納する
        model.addAttribute("result", result);

        // 遷移先ページ（結果画面）の論理名を返却する
        return "CalcOutputPage";
    }
}