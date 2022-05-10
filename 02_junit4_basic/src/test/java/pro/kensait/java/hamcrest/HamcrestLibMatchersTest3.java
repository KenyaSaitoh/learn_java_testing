package pro.kensait.java.hamcrest;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class HamcrestLibMatchersTest3 {

    @Test
    public void test01() {
        Person actual = new Person(1, "Alice");
        assertThat(actual, hasProperty("name"));
    }

    @Test
    public void test02() {
        Person actual = new Person(1, "Alice");
        assertThat(actual, hasProperty("name", is("Alice")));
    }

    public static class Person {
        private Integer id;
        private String name;
        public Person(Integer id, String name) {
            this.id = id;
            this.name = name;
        }
        public Integer getId() {
            return id;
        }
        public void setId(Integer id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}