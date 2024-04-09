package pro.kensait.spring.person.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @ModelAttribute("personSession") // @SessionAttributesと名前を一致させる
    public PersonSession initSession(){
        logger.info("[ PersonController#initSession ]");
        return new PersonSession();
    }

    // トップ画面に遷移する
    @RequestMapping("/")
    public String index() {
        logger.info("[ PersonController#index ]");
        return "redirect:/viewList";
    }

    // 入力画面に遷移する
    @RequestMapping("/create")
    public String createPerson(SessionStatus sessionStatus) {
        logger.info("[ PersonController#createPerson ]");
        // セッションスコープに格納されたpersonSessionに削除マークを付ける（すぐに削除はされない）
        sessionStatus.setComplete();
        return "PersonInputPage";
    }

    // 確認画面に遷移する
    @RequestMapping("/confirm")
    public String confirm(@Validated PersonSession personSession,
            BindingResult errors) {
        logger.info("[ PersonController#confirm ]");

        if (errors.hasErrors()) {
            return "PersonInputPage";
        }
        return "PersonUpdatePage";
    }

    // 入力画面に戻る
    @RequestMapping("/back")
    public String back() {
        logger.info("[ PersonController#back ]");
        return "PersonInputPage";
    }

    // 人物を更新・追加する
    @RequestMapping("/update")
    public String updatePerson(@Validated PersonSession personSession,
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
            personService.replacePerson(person);
        }

        // セッションスコープに格納されたpersonSessionに処理終了マークを付ける（すぐに削除はされない）
        sessionStatus.setComplete();
        return "redirect:/viewList";
    }

    // 人物を編集する
    @RequestMapping("/edit")
    public String editPerson(@RequestParam("personId") Integer personId,
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
    @RequestMapping("/remove")
    public String removePerson(@RequestParam("personId") Integer personId) {
        logger.info("[ PersonController#removePerson ]");
        personService.removePerson(personId);
        return "redirect:/viewList";
    }

    // 人物リストを表示する
    @RequestMapping("/viewList")
    public String viewPersonList(Model model) {
        logger.info("[ PersonController#viewPersonList ]");
        List<Person> personList = personService.getPersonsAll();
        model.addAttribute("personList", personList);
        return "PersonTablePage";
    }
}