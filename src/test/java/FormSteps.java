
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.CollectionCondition.size;
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

    public static void setBirthDateHard(String date) {
        String[] data = date.split("\\s");//parse date
        $("#dateOfBirthInput").click();
        $x("//select[@class='react-datepicker__month-select']//option[contains(text(),'"+data[1]+"')]").click();
        $x("//select[@class='react-datepicker__year-select']//option[contains(text(),'"+data[2]+"')]").click();
        $x("//div[@class='react-datepicker__week']//div[@class='react-datepicker__day react-datepicker__day--0"+data[0]+"']").click();
    }

    public static void setSubject(String subj) {
        $("#subjectsInput").scrollIntoView(true).setValue(subj).pressEnter();//mobile
    }

            public static void setHobby(String hobby) {
            ElementsCollection hobbies = $$x("//input[@type='checkbox']");
            System.out.println(hobbies.size());
            hobbies.shouldHave(size(3));
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


    public static void loadPicture(String path) {
        $x("//input[@type='file']").scrollIntoView(true).uploadFromClasspath(path);

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

    public static void fillTheForm(TestDataFaker test) {
        setFirstName(test.FIRSTNAME);
        setLastName(test.LASTNAME);
        setEMail(test.EMAIL);
       setGender(test.GENDER);
        setMobile(test.MOBILE);
        //setBirthDateHard(test.BIRTHDATE);
        setSubject(test.SUBJECT);
        setHobby(test.HOBBY);
        loadPicture(test.PICTURE);
        setAddress(test.ADDRESS);
        setState(test.STATE);
        setCity(test.CITY);
        submit();
    }
}