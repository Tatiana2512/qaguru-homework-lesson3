
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class FormSteps {

    public static void setFirstName(String first) {
        $("#firstName").scrollIntoView(true).setValue(first);//1st name
    }

    public static void setLastName(String last) {
        $("#lastName").scrollIntoView(true).setValue(last);//last name
    }

    public static void setEMail(String email) {
        $("#userEmail").scrollIntoView(true).setValue(email);//email
    }

    public static void setGender(String gender) { //need to refactor this;same issue as setBirthDateHard1
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

    public static void setMobile(String mobile) {
        $("#userNumber").scrollIntoView(true).setValue(mobile);//mobile
    }

    public static void setBirthDateSimple(String date) {
        $("#dateOfBirthInput").scrollIntoView(true).sendKeys(Keys.CONTROL + "A");//date - select all
        $("#dateOfBirthInput").scrollIntoView(true).sendKeys(Keys.SPACE);//clear field by press spacebar
        $x("//input[@id='dateOfBirthInput']").scrollIntoView(true).setValue(date).pressEnter();//set new date
    }

    public static void setBirthDateHard1(String date) {
        String[] data = date.split("\\s");//parse date
        $("#dateOfBirthInput").click();
        $x("//select[@class='react-datepicker__month-select']//option[contains(text(),data[1])]");// can not recognize value from data[1], dont know why
        $x("//select[@class='react-datepicker__year-select']//option[contains(text(),data[2])]");
        $x("//div[@class='react-datepicker__week']//div[contains(text(),data[0]]");
    }

    public static void setSubject(String subj) {
        $("#subjectsInput").scrollIntoView(true).setValue(subj).pressEnter();//mobile
    }

    public static void setHobby(String hobby) { //need to refactor this;same issue as setBirthDateHard1
        switch (hobby) {
            case ("Sports"):
                $("#hobbiesWrapper").$(byText(hobby)).scrollIntoView(true).click();
                break;
            case ("Reading"):
                $("#hobbiesWrapper").$(byText(hobby)).scrollIntoView(true).click();
                break;
            case ("Music"):
                $("#hobbiesWrapper").$(byText(hobby)).scrollIntoView(true).click();
                break;
            default:
                break;
        }
    }

    public static void loadPicture(String path) {
        $x("//input[@type='file']").scrollIntoView(true).setValue(path);//troubles with relative path

    }

    public static void setAddress(String address) {
        $x("//textarea").scrollIntoView(true).setValue(address);
    }

    public static void setState(String state) {
        $x("//input[@id='react-select-3-input']").scrollIntoView(true).setValue(state).pressEnter();//set state
    }

    public static void setCity(String city) {
        $x("//input[@id='react-select-4-input']").scrollIntoView(true).setValue(city).pressEnter();//set city
    }

    public static void submit() {
        $(byText("Submit")).scrollIntoView(true).click();
    }

    public static void fillTheForm() {
        setFirstName(TestData.FIRSTNAME);
        setLastName(TestData.LASTNAME);
        setEMail(TestData.EMAIL);
        setGender(TestData.GENDER);
        setMobile(TestData.MOBILE);
        setBirthDateSimple(TestData.BIRTHDATE);
        setSubject(TestData.SUBJECT);
        setHobby(TestData.HOBBY);
        loadPicture(TestData.PICTURE);
        setAddress(TestData.ADDRESS);
        setState(TestData.STATE);
        setCity(TestData.CITY);
        submit();
    }
}