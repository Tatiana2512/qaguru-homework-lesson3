import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class FormTests {

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1980x800";
    }

    @Test
    public void fillTheFormTest() {
        open("/automation-practice-form");
        //Selenide.executeJavaScript("document.body.style.zoom='80%'"); перестад работать click() после этой команды
        executeJavaScript("document.querySelector(\"footer\").hidden = 'true';document.querySelector(\"#fixedban\").hidden = 'true'");  // -удаление футера
        FormSteps.fillTheForm();
        $x("//div[@class='table-responsive']").shouldBe(Condition.visible);
        $(".table-responsive").shouldHave(text("Student Name " + TestData.FIRSTNAME + " " + TestData.LASTNAME),
                text("Mobile " + TestData.MOBILE),
                text("Picture img.jpeg"),
                text("Student Email " + TestData.EMAIL),
                text("Gender " + TestData.GENDER),
                text("Date of Birth 03 October,1978"),
                text("Subjects " + TestData.SUBJECT),
                text("Hobbies " + TestData.HOBBY),
                text("Address " + TestData.ADDRESS),
                text("State and City " + TestData.STATE + " " + TestData.CITY));
        $("#closeLargeModal").scrollIntoView(true).click();
    }
}