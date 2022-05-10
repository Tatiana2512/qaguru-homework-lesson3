package ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.TestDataFaker;
import helper.Attachments;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationFormPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static io.qameta.allure.Allure.step;

public class RegistrationFormTest {

    @BeforeAll
    static void setUp() {
        //Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1400x800";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

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

        step("Verify accepted data", () ->{
            page.checkTheForm(student);
        });

    }

    @AfterEach
    void addAttachments() {
        Attachments.screenshotAs("Last screenshot");
        Attachments.pageSource();
        Attachments.browserConsoleLogs();
        Attachments.addVideo();
        closeWebDriver();
    }

}