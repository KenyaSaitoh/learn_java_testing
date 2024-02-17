package pro.kensait.junit5.assertion1;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import pro.kensait.junit5.assertion.Person1;
import pro.kensait.junit5.assertion.Person2;
import pro.kensait.junit5.assertion.Person3;

/*
 * インスタンスの等価性を検証するためのテストクラス
 */
public class AssertEqualsTest_2 {

    // 同じ値であること（等価性）を検証する
    @Test
    void test_Equals() {
        LocalDateTime now = LocalDateTime.now();
        Person1 expected = new Person1("Alice", 25, now);
        Person1 actual = new Person1("Alice", 25, now);
        assertEquals(expected, actual);
    }

    // 最終更新時間を除外して、同じ値であること（等価性）を検証する
    @Test
    void test_Equals_WithoutLastUpdateTime() throws Exception {
        Person1 expected = new Person1("Alice", 25, LocalDateTime.now());
        Thread.sleep(1000);
        Person1 actual = new Person1("Alice", 25, LocalDateTime.now());
        assertNotEquals(expected, actual);
        assertTrue(areEqualIgnoringLastUpdateTime(expected, actual));
    } 

    // ヘルパーメソッド（LastUpdateTimeを除いて等価性を判定する）
    private boolean areEqualIgnoringLastUpdateTime(Person1 p1, Person1 p2) {
        return Objects.equals(p1.getName(), p2.getName()) &&
                p1.getAge() == p2.getAge();
    }

    // 最終更新時間を意識して、同じ値であること（等価性）を検証する
    @Test
    void test_Equals_ConsideringLastUpdateTime() throws Exception {
        Person2 expected = new Person2("Alice", 25, LocalDateTime.now());
        Thread.sleep(1000);
        Person2 actual = new Person2("Alice", 25, LocalDateTime.now());
        assertEquals(expected, actual);
    }

    // リストの並び順を踏まえて、同じ値でないこと（非等価性）を検証する
    @Test
    void test_EotEquals_WithOrdering() {
        List<String> expectedHobbies = Arrays.asList("BASEBALL", "MUSIC", "MOVIE");
        List<String> actualHobbies = Arrays.asList("MUSIC", "MOVIE", "BASEBALL");
        Person3 expected = new Person3("Alice", 25, expectedHobbies);
        Person3 actual = new Person3("Alice", 25, actualHobbies);
        assertNotEquals(expected, actual);
    }

    // リストの並び順を無視して、同じ値であること（等価性）を検証する
    @Test
    void test_Equals_IgnoringOrdering() {
        List<String> expectedHobbies = Arrays.asList("BASEBALL", "MUSIC", "MOVIE");
        List<String> actualHobbies = Arrays.asList("MUSIC", "MOVIE", "BASEBALL");
        Person3 expected = new Person3("Alice", 25, expectedHobbies);
        Person3 actual = new Person3("Alice", 25, actualHobbies);

        // リストを対象外にして検証する
        assertTrue(areEqualIgnoringHobbies(expected, actual));

        // リストをソートし、リスト同士を検証する
        Collections.sort(expected.getHobbies());
        Collections.sort(actual.getHobbies());
        assertIterableEquals(expected.getHobbies(), actual.getHobbies());

        // リストをソート後、Person3同士を検証する
        assertEquals(expected, actual);
    }

    // ヘルパーメソッド（リストHobbiesを除いて等価性を判定する）
    private boolean areEqualIgnoringHobbies(Person3 p1, Person3 p2) {
        return Objects.equals(p1.getName(), p2.getName()) &&
                p1.getAge() == p2.getAge();
    }
}