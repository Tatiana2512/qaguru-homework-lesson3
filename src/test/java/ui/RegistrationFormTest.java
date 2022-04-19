package ui;

import com.codeborne.selenide.Configuration;
import data.TestDataFaker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationFormPage;

public class RegistrationFormTest {

    @BeforeAll
    static void setUp() {
        //Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1400x800";
    }

    @Test
    public void fillTheFormTest() {
        TestDataFaker student = new TestDataFaker();
        RegistrationFormPage page = new RegistrationFormPage();
        page.openPage().fillTheForm(student).checkTheForm(student);

    }

}