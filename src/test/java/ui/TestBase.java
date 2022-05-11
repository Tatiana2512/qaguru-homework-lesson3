package ui;

import com.codeborne.selenide.Configuration;
import data.config.JenkinsConfig;
import helper.Attachments;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    @BeforeAll
    static void setUp() {

        JenkinsConfig config = ConfigFactory.create(JenkinsConfig.class);
//        String login = config.login();
//        String password = config.password();
//        String baseURL = config.baseURL();
////        String remoteBrowserSize = config.browserSize();
////        String remoteBrowserURL = config.remoteBrowserURL();
//        String remoteBrowserConfig = config.remoteConfig();

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1400x800";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }


//    @AfterEach
//    void addAttachments() {
//        Attachments.screenshotAs("Last screenshot");
//        Attachments.pageSource();
//        Attachments.browserConsoleLogs();
//        Attachments.addVideo();
//        closeWebDriver();
//    }
}
