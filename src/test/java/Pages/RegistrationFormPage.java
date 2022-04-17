package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import test_data.TestDataFaker;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {
    //locators

    SelenideElement FirstNameInput = $("#firstName");

    SelenideElement LastNameInput = $("#lastName");

    SelenideElement EMailInput = $("#userEmail");

    ElementsCollection GenderRadio = $$x("//div[@id='genterWrapper']//input");

    SelenideElement MobileInput = $("#userNumber");

    SelenideElement SubjectInput = $("#subjectsInput");

    ElementsCollection HobbyCheck = $$x("//input[@type='checkbox']");

    SelenideElement PictureInput = $x("//input[@type='file']");

    SelenideElement AddressInput = $x("//textarea");

    SelenideElement StateDropDown = $x("//input[@id='react-select-3-input']");

    SelenideElement CityDropDown = $x("//input[@id='react-select-4-input']");

    SelenideElement Button = $(byText("Submit"));

    //actions


    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        executeJavaScript("document.querySelector(\"footer\").hidden = 'true';document.querySelector(\"#fixedban\").hidden = 'true'");  // -удаление футера
        //$x("//h5").shouldHave(text("Student Registration Form"));
        return this;
    }

    public RegistrationFormPage setFirstName(String first) {
        FirstNameInput.scrollIntoView(true).setValue(first);//1st name
        return this;
    }

    public RegistrationFormPage setLastName(String last) {
        LastNameInput.scrollIntoView(true).setValue(last);//last name
        return this;
    }

    public RegistrationFormPage setEMail(String email) {
        EMailInput.scrollIntoView(true).setValue(email);//email
        return this;
    }

    public RegistrationFormPage setGender(String gender) {
        switch (gender) {
            case ("Male"):
                GenderRadio.get(0).parent().scrollIntoView(true).click();
                break;
            case ("Female"):
                GenderRadio.get(1).parent().scrollIntoView(true).click();
                break;
            case ("Other"):
                GenderRadio.get(2).parent().scrollIntoView(true).click();
                break;
            default:
                break;
        }
        return this;
    }

    public RegistrationFormPage setMobile(String mobile) {
        MobileInput.scrollIntoView(true).setValue(mobile);//mobile
        return this;
    }


    public RegistrationFormPage setBirthDateManually(Date date) {
        String[] data = date.toString().split("\\s");//parse date
        $("#dateOfBirthInput").click();
        $x("//select[@class='react-datepicker__month-select']//option[contains(text(),'" + data[1] + "')]").click();
        $x("//select[@class='react-datepicker__year-select']//option[contains(text(),'" + data[5] + "')]").click();
        $x("//div[(contains(@class,'react-datepicker__day react-datepicker__day--0" + data[2] + "')) and not(contains(@class,'day--outside-month'))]").click();
        return this;
    }

    public String birthDataToAssert(Date date) {
        String[] data = date.toString().split("\\s");
        String day = data[2];
        String month = Month.of(1 + date.getMonth()).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String year = data[5];
        return (day + " " + month + "," + year);
    }

    public RegistrationFormPage setSubject(String subj) {
        SubjectInput.scrollIntoView(true).setValue(subj).pressEnter();//mobile
        return this;
    }

    public RegistrationFormPage setHobby(String hobby) {
        switch (hobby) {
            case ("Sports"):
                HobbyCheck.get(0).parent().click();
                break;
            case ("Reading"):
                HobbyCheck.get(1).parent().click();
                break;
            case ("Music"):
                HobbyCheck.get(2).parent().click();
                break;
            default:
                break;
        }
        return this;
    }


    public RegistrationFormPage loadPicture(String path) {
        PictureInput.scrollIntoView(true).uploadFromClasspath(path);
        return this;
    }

    public RegistrationFormPage setAddress(String address) {
        AddressInput.scrollIntoView(true).setValue(address);
        return this;
    }

    public RegistrationFormPage setState(String state) {
        StateDropDown.scrollIntoView(true).setValue(state).pressEnter();//set state
        return this;
    }

    public RegistrationFormPage setCity(String city) {
        CityDropDown.scrollIntoView(true).setValue(city).pressEnter();//set city
        return this;
    }

    public RegistrationFormPage submit() {
        Button.scrollIntoView(true).click();
        return this;
    }

    public RegistrationFormPage fillTheForm(TestDataFaker test) {

        setFirstName(test.FIRSTNAME);
        setLastName(test.LASTNAME);
        setEMail(test.EMAIL);
        setGender(test.GENDER);
        setMobile(test.MOBILE);
        setBirthDateManually(test.BIRTHDATE);
        setSubject(test.SUBJECT);
        setHobby(test.HOBBY);
        loadPicture(test.PICTURE);
        setAddress(test.ADDRESS);
        setState(test.STATE);
        setCity(test.CITY);
        submit();

        return this;

    }

    public void checkTheForm(TestDataFaker test) {
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
