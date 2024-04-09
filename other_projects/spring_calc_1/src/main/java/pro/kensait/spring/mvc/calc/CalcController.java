package pro.kensait.spring.mvc.calc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CalcController {

    // @ModelAttributeアノテーションを付与したメソッドを宣言すると、以下の処理が自動的に行われる
    // ・リクエスト時にパラメータがCalcFormインスタンスに自動的にセットされる
    // ・当該CalcFormインスタンスがModelに自動的に登録される
    @ModelAttribute("calcForm")
    public CalcForm initCalcForm(){
        System.out.println("[ CalcController#initCalcForm ]");
        return new CalcForm();
    }

    @RequestMapping("/")
    public String index() {
        return "CalcInputPage";
    }

    @RequestMapping("/add")
    public String add(CalcForm calcForm, Model model) {

        // ビジネスロジックを呼び出す
        // ここではサービスに処理を委譲せず、直接実装
        double result = calcForm.getParam1() + calcForm.getParam2();

        // 計算結果をModelに格納する
        model.addAttribute("result", result);

        // 遷移先ページ（結果画面）の論理名を返却する
        return "CalcOutputPage";
    }
}