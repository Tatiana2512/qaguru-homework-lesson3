package helper;

import com.codeborne.selenide.Selenide;
import data.config.JenkinsConfig;
import io.qameta.allure.Attachment;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.logging.LogType.BROWSER;

@Tag("attachment")
public class Attachments {

    @Attachment(value = "{attachName}", type = "text/plain")
    public static String attachAsText(String attachName, String message) {
        return message;
    }

    @Attachment(value = "Page source", type = "text/plain")
    public static byte[] pageSource() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] screenshotAs(String attachName) {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static void browserConsoleLogs() {
        attachAsText(
                "Browser console logs",
                String.join("\n", Selenide.getWebDriverLogs(BROWSER))
        );
    }

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String addVideo() {
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + getVideoUrl(getSessionId())
                + "' type='video/mp4'></video></body></html>";
    }

    public static URL getVideoUrl(String sessionId) {
        //JenkinsConfig configure = ConfigFactory.create(JenkinsConfig.class);


        //String.format("https://%s/video",configure.remoteBrowserURL()).concat(sessionId+".mp4");

        //working,default
        String videoUrl = "https://selenoid.autotests.cloud/video/" + sessionId + ".mp4";

        //working, from Stacy
//        String remoteVideoSource = System.getProperty("remoteVideoSource", "https://selenoid.autotests.cloud/video");
//        String videoUrl = remoteVideoSource + sessionId + ".mp4";


        try {
            return new URL(videoUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getSessionId(){
        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
    }
}
