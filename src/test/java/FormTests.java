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
        Configuration.browserSize = "800x800";
    }

    @Test
    public void fillTheFormTest() {
        TestDataFaker student = new TestDataFaker();
        open("/automation-practice-form");
        //Selenide.executeJavaScript("document.body.style.zoom='80%'"); перестад работать click() после этой команды
        executeJavaScript("document.querySelector(\"footer\").hidden = 'true';document.querySelector(\"#fixedban\").hidden = 'true'");  // -удаление футера
        FormSteps.fillTheForm(student);
        $x("//div[@class='table-responsive']").shouldBe(Condition.visible);
        $(".table-responsive").shouldHave(text("Student Name " + student.FIRSTNAME + " " + student.LASTNAME),
                text("Mobile " + student.MOBILE),
                text("Picture img.jpeg"),
                text("Student Email " + student.EMAIL),
                text("Gender " + student.GENDER),
                text("Date of Birth 03 October,1978"),
                text("Subjects " + student.SUBJECT),
                text("Hobbies " + student.HOBBY),
                text("Address " + student.ADDRESS),
                text("State and City " + student.STATE + " " + student.CITY));
        $("#closeLargeModal").scrollIntoView(true).click();
    }
}