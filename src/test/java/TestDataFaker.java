import com.github.javafaker.Faker;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestDataFaker {

        Faker ukFaker = new Faker(new Locale("en-GB"));
        Random rand = new Random();
        final List<String> hobby = Arrays.asList("Sports", "Reading", "Music");
        final List<String> states = Arrays.asList("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
        final List<String> city = Arrays.asList("Delhi", "Gurgaon", "Noida", "Agra", "Lucknow", "Merrut", "Karnal", "Panipat", "Jaipur", "Jaiselmer");


        public final String FIRSTNAME = ukFaker.name().firstName();

        public final String LASTNAME = ukFaker.name().lastName();

        public final String EMAIL = ukFaker.internet().emailAddress();

        public final String GENDER = ukFaker.demographic().sex();

        public final String MOBILE = ukFaker.number().digits(10);

        public final String SUBJECT = "Maths";

        public final String HOBBY = hobby.get(rand.nextInt(hobby.size()));

        public final String PICTURE = "img.jpeg";

        public final String ADDRESS = ukFaker.address().fullAddress();

        public final String STATE = states.get(rand.nextInt(states.size()));

        public String CITY = getCity(STATE);

        public String getCity(String someString) {
                String result=null;
                switch (someString) {
                        case ("NCR"):
                                result = city.subList(0,2).get(rand.nextInt(city.subList(0,2).size()));
                                break;
                        case ("Uttar Pradesh"):
                                result= city.subList(3,5).get(rand.nextInt(city.subList(3,5).size()));
                                break;
                        case ("Haryana"):
                                result= city.subList(6,7).get(rand.nextInt(city.subList(6,7).size()));
                                break;
                        case ("Rajasthan"):
                                result= city.subList(8,9).get(rand.nextInt(city.subList(8,9).size()));
                                break;
                        default:
                                break;
                }
                return result;
        }

        public final String BIRTHDATE = setBirthdate();

        public String setBirthdate(){
                Format formatter = new SimpleDateFormat("dd MMM yyyy");
                return formatter.format(ukFaker.date().birthday());
        }



}




