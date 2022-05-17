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

        Configuration.baseUrl = config.baseURL();
        Configuration.browserSize = System.getProperty("browserSize", config.remoteBrowserSize());
        Configuration.remote = System.getProperty("remoteUrl", String.format("https://%s:%s@%s/wd/hub", config.login(), config.password(), config.remoteBrowserURL()));
        Configuration.browser = System.getProperty("browser", "opera");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
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
