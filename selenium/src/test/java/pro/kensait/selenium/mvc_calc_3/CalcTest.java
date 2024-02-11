package pro.kensait.selenium.mvc_calc_3;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

class CalcTest {

    @Test
    void testCalculation() {
        // Step 1: Open the specified URL
        Selenide.open("http://localhost:8080/");

        // Step 2: Input 30 into the element with ID 'param1'
        $("#param1").setValue("30");

        // Step 3: Input 10 into the element with ID 'param2'
        $("#param2").setValue("10");

        // Step 4: Click the element with ID 'addButton'
        $("#addButton").click();

        // Step 5: Verify that the page title is 'CalcOutputPage'
        assertEquals("CalcOutputPage", title());

        // Step 6: Verify that the result is 40
        $("#result").shouldHave(text("40"));

        // Step 7: Go back to the previous page
        Selenide.back();

        // Step 8: Input 500 into the element with ID 'param1'
        $("#param1").setValue("500");

        // Step 9: Input 500 into the element with ID 'param2'
        $("#param2").setValue("500");

        // Step 10: Click the element with ID 'subtractButton'
        $("#subtractButton").click();

        // Step 11: Verify that the page title is 'CalcInputPage'
        assertEquals("CalcInputPage", title());

        // Step 12: Verify that an error exists in any location under 'globalErrors'
        $$("#globalErrors").find(Condition.exist);
    }
}
