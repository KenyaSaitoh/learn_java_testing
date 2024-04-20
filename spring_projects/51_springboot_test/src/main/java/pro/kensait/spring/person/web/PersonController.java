package pro.kensait.spring.person.web;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pro.kensait.spring.person.service.Person;
import pro.kensait.spring.person.service.PersonService;

@Controller
@SessionAttributes("personSession")
public class PersonController {
    private static final Logger logger = LoggerFactory.getLogger(
            PersonController.class);

    // インジェクションポイント
    @Autowired
    private PersonService personService;

    // インジェクションポイント
    @Autowired
    private MessageSource messageSource;
    
    @ModelAttribute("personSession") // @SessionAttributesと名前を一致させる
    public PersonSession initSession(){
        logger.info("[ PersonController#initSession ]");
        return new PersonSession();
    }

    // トップ画面に遷移する
    @GetMapping("/")
    public String index() {
        logger.info("[ PersonController#index ]");
        return "redirect:/viewList";
    }

    // 入力画面に遷移する
    @PostMapping("/toCreate")
    public String toCreate(SessionStatus sessionStatus) {
        logger.info("[ PersonController#toCreate ]");
        // セッションスコープに格納されたpersonSessionに削除マークを付ける（すぐに削除はされない）
        sessionStatus.setComplete();
        return "PersonInputPage";
    }

    // 確認画面に遷移する
    @PostMapping("/toConfirm")
    public String toConfirm(@Validated PersonSession personSession,
            BindingResult errors, Model model) {
        logger.info("[ PersonController#toConfirm ]");
        if (errors.hasErrors()) {
            // メッセージソースよりエラーメッセージを取得し、Modelに格納する
            String errorMessage = messageSource.getMessage("error.occured", null,
                    Locale.JAPANESE);
            model.addAttribute("errorMessage", errorMessage);
        }
        return "PersonUpdatePage";
    }

    // 入力画面に戻る
    @GetMapping("/back")
    public String back() {
        logger.info("[ PersonController#back ]");
        return "PersonInputPage";
    }

    // 人物を更新・追加する
    @PostMapping("/update")
    public String update(@Validated PersonSession personSession,
            SessionStatus sessionStatus) {
        logger.info("[ PersonController#updatePerson ]");

        // 更新か追加を判定する
        if (personSession.getPersonId() != null) {
            Person person = new Person(personSession.getPersonId(),
                    personSession.getPersonName(),
                    personSession.getAge(), personSession.getGender());
            personService.updatePerson(person);
        } else {
            Person person = new Person(personSession.getPersonName(),
                    personSession.getAge(), personSession.getGender());
            personService.createPerson(person);
        }

        // セッションスコープに格納されたpersonSessionに処理終了マークを付ける（すぐに削除はされない）
        sessionStatus.setComplete();
        return "redirect:/viewList";
    }

    // 人物を編集する
    @PostMapping("/edit")
    public String edit(@RequestParam("personId") Integer personId,
            PersonSession personSession) {
        logger.info("[ PersonController#editPerson ]");
        Person person = personService.getPerson(personId);
        personSession.setPersonId(personId);
        personSession.setPersonName(person.getPersonName());
        personSession.setAge(person.getAge());
        personSession.setGender(person.getGender());
        return "PersonInputPage";
    }

    // 人物を削除する
    @PostMapping("/remove")
    public String remove(@RequestParam("personId") Integer personId) {
        logger.info("[ PersonController#remove ]");
        personService.removePerson(personId);
        return "redirect:/viewList";
    }

    // 人物リストを表示する
    @GetMapping("/viewList")
    public String viewList(Model model) {
        logger.info("[ PersonController#viewList ]");
        List<Person> personList = personService.getPersonsAll();
        model.addAttribute("personList", personList);
        return "PersonTablePage";
    }
}