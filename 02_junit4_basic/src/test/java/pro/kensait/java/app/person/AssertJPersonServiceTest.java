package pro.kensait.java.app.person;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class AssertJPersonServiceTest {
    // 検証で利用するPersonを定数として準備する
    private static final Person ALICE = new Person(1, "Alice", 25, "female", null);
    private static final Person BOB = new Person(2, "Bob", 35, "male", null);
    private static final Person CAROL = new Person(3, "Carol", 30, "female", null);
    private static final Person DAVE = new Person(4, "Dave", 23, "male", null);
    private static final Person ELLEN = new Person(5, "Ellen", 33, "male", null);

    PersonService personService;

    @Before
    public void setUp() {
        personService = new PersonService();
    }

    @Test
    public void getPerson_ReturnsPerson() {
        Person actual = personService.getPerson(1);
        Person expected = ALICE;
        assertThat(actual, is(expected));
    }

    @Test
    public void getPersonsAll_ReturnsPersonsAll() {
        List<Person> actual = personService.getPersonsAll();
        List<Person> expected = List.of(ALICE, BOB, CAROL, DAVE, ELLEN);
        assertThat(actual).containsExactlyElementsOf(expected);
        assertThat(actual).containsOnlyOnceElementsOf(expected);
    }

    @Test
    public void getPersonsByLowerAge_ReturnsPersons() {
        List<Person> actual = personService.getPersonsByLowerAge(30);
        List<Person> expected = List.of(BOB, CAROL, ELLEN);
        // 以下はエラー
        // assertThat(actual).containsExactlyElementsOf(expected);
        // 以下はソート順がぐちゃぐちゃでもOK
        assertThat(actual).containsOnlyOnceElementsOf(expected);
    }

    @Test
    public void getPersonsAllSortByAge_ReturnsSortedPersons() {
        List<Person> actual = personService.getPersonsAllSortByAge(true);
        List<Person> expected = List.of(DAVE, ALICE, CAROL, ELLEN, BOB);
        // ソートまで一致を確認
        assertThat(actual).containsSequence(expected);
    }
}