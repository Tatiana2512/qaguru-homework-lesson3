package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import data.TestDataFaker;
import org.openqa.selenium.Keys;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class RegistrationFormSteps {

    public void setFirstName(String first) {
        $("#firstName").scrollIntoView(true).setValue(first);//1st name
    }

    public void setLastName(String last) {
        $("#lastName").scrollIntoView(true).setValue(last);//last name
    }

    public void setEMail(String email) {
        $("#userEmail").scrollIntoView(true).setValue(email);//email
    }

    public void setGender(String gender) { //need to refactor this;same issue as setBirthDateHard1
        switch (gender) {
            case ("Male"):
                $("#genterWrapper").$(byText(gender)).scrollIntoView(true).click();
                break;
            case ("Female"):
                $("#genterWrapper").$(byText(gender)).scrollIntoView(true).click();
                break;
            case ("Other"):
                $("#genterWrapper").$(byText(gender)).scrollIntoView(true).click();
                break;
            default:
                break;
        }
    }

    public void setMobile(String mobile) {
        $("#userNumber").scrollIntoView(true).setValue(mobile);//mobile
    }

    public void setBirthDateSimple(String date) {
        $("#dateOfBirthInput").scrollIntoView(true).sendKeys(Keys.CONTROL + "A");//date - select all
        $("#dateOfBirthInput").scrollIntoView(true).sendKeys(Keys.SPACE);//clear field by press spacebar
        $x("//input[@id='dateOfBirthInput']").scrollIntoView(true).setValue(date).pressEnter();//set new date
    }

    public void setBirthDateHard(Date date) {
        String[] data = date.toString().split("\\s");//parse date
        $("#dateOfBirthInput").click();
        $x("//select[@class='react-datepicker__month-select']//option[contains(text(),'" + data[1] + "')]").click();
        $x("//select[@class='react-datepicker__year-select']//option[contains(text(),'" + data[5] + "')]").click();
        $x("//div[(contains(@class,'react-datepicker__day react-datepicker__day--0" + data[2] + "')) and not(contains(@class,'day--outside-month'))]").click();
    }

    public String birthDataToAssert(Date date) {
        String[] data = date.toString().split("\\s");
        String day = data[2];
        String month = Month.of(1 + date.getMonth()).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String year = data[5];
        return (day + " " + month + "," + year);
    }

    public void setSubject(String subj) {
        $("#subjectsInput").scrollIntoView(true).setValue(subj).pressEnter();//mobile
    }

    public void setHobby(String hobby) {
        ElementsCollection hobbies = $$x("//input[@type='checkbox']");
        switch (hobby) {
            case ("Sports"):
                hobbies.get(0).parent().click();
                break;
            case ("Reading"):
                hobbies.get(1).parent().click();
                break;
            case ("Music"):
                hobbies.get(2).parent().click();
                break;
            default:
                break;
        }
    }


    public void loadPicture(String path) {
        $x("//input[@type='file']").scrollIntoView(true).uploadFromClasspath(path);

    }

    public void setAddress(String address) {
        $x("//textarea").scrollIntoView(true).setValue(address);
    }

    public void setState(String state) {
        $x("//input[@id='react-select-3-input']").scrollIntoView(true).setValue(state).pressEnter();//set state
    }

    public void setCity(String city) {
        $x("//input[@id='react-select-4-input']").scrollIntoView(true).setValue(city).pressEnter();//set city
    }

    public void submit() {
        $(byText("Submit")).scrollIntoView(true).click();
    }

    public void toFillTheForm(TestDataFaker test) {
        setFirstName(test.FIRSTNAME);
        setLastName(test.LASTNAME);
        setEMail(test.EMAIL);
        setGender(test.GENDER);
        setMobile(test.MOBILE);
        setBirthDateHard(test.BIRTHDATE);
        setSubject(test.SUBJECT);
        setHobby(test.HOBBY);
        loadPicture(test.PICTURE);
        setAddress(test.ADDRESS);
        setState(test.STATE);
        setCity(test.CITY);
        submit();
    }

    public void toCheckTheForm(TestDataFaker test) {
        $(".table-responsive").shouldBe(Condition.visible)
                .shouldHave(text("Student Name " + test.FIRSTNAME + " " + test.LASTNAME),
                        text("Mobile " + test.MOBILE),
                        text("Picture img.jpeg"),
                        text("Student Email " + test.EMAIL),
                        text("Gender " + test.GENDER),
                        text("Date of Birth " + birthDataToAssert(test.BIRTHDATE)),
                        text("Subjects " + test.SUBJECT),
                        text("Hobbies " + test.HOBBY),
                        text("Address " + test.ADDRESS),
                        text("State and City " + test.STATE + " " + test.CITY));
        $("#closeLargeModal").scrollIntoView(true).click();
    }
}