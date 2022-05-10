package pro.kensait.java.app.person;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PersonServiceTest {
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
    public void find_ReturnsPerson() {
        Person actual = personService.find(1);
        Person expected = ALICE;
        assertThat(actual, is(expected));
    }

    @Test
    public void findAll_ReturnsPersonsAll() {
        List<Person> actual = personService.findAll();
        List<Person> expected = List.of(ALICE, BOB, CAROL, DAVE, ELLEN);
        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }

    @Test
    public void findPersonByLowerAge_ReturnsPersons() {
        List<Person> actual = personService.findPersonByLowerAge(30);
        List<Person> expected = List.of(BOB, CAROL, ELLEN);
        // 以下はエラー
        // assertThat(actual, contains(expected.toArray()));
        // 以下はソート順がぐちゃぐちゃでもOK
        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }

    @Test
    public void findAllSortByAge_ReturnsPersons() {
        List<Person> actual = personService.findAllSortByAge(true);
        List<Person> expected = List.of(DAVE, ALICE, CAROL, ELLEN, BOB);
        // ソートまで一致を確認
        assertThat(actual, contains(expected.toArray()));
    }
}