package ui;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.TestDataFaker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.RegistrationFormPage;

import static io.qameta.allure.Allure.step;

public class RegistrationFormTest extends TestBase {

    @Test
    @DisplayName("Check Registration Form")
    public void fillTheFormTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        TestDataFaker student = new TestDataFaker();
        RegistrationFormPage page = new RegistrationFormPage();
        step("Open registration form", () -> {
            page.openPage();
        });

        step("Fill registration form", () -> {
            page.fillTheForm(student);
        });

        step("Verify accepted data", () -> {
            page.checkTheForm(student);
        });

    }

}