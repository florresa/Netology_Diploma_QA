package netology.Data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private static String locale = System.getProperty("test.locale");

    private DataGenerator() {
    }

    public static CardInfo getApprovedCard() {
        return new CardInfo("4444444444444441", generateRandomMonth(), generateFutureYear(), generateName(locale), generateValidCvc());
    }

    public static CardInfo getDeclinedCard() {
        return new CardInfo("4444444444444442", generateRandomMonth(), generateFutureYear(), generateName(locale), generateValidCvc());
    }

    public static CardInfo getEmptyCard() {
        return new CardInfo("", "", "", "", "");
    }

    public static CardInfo getRandomValidCard() {
        return new CardInfo(generateValidCardNumber(), generateRandomMonth(), generateFutureYear(), generateName(locale), generateValidCvc());
    }

    public static CardInfo getRandomInvalidCard() {
        return new CardInfo(generateInvalidCardNumber(), generateRandomMonth(), generateFutureYear(), generateName(locale), generateValidCvc());
    }

    public static CardInfo getRandomInvalidMonth() {
        return new CardInfo("4444444444444441", generatePastMonth(), generateCurrentYear(), generateName(locale), generateValidCvc());
    }

    public static CardInfo getRandomInvalidMonthOneSymbol() {
        return new CardInfo("4444444444444441", generateInvalidMonth(), generateFutureYear(), generateName(locale), generateValidCvc());
    }

    public static CardInfo getRandomInvalidYear() {
        return new CardInfo("4444444444444441", generateRandomMonth(), generatePastYear(), generateName(locale), generateValidCvc());
    }

    public static CardInfo getRandomOneSymbolYear() {
        return new CardInfo("4444444444444441", generateRandomMonth(), generateInvalidYear(), generateName(locale), generateValidCvc());
    }

    public static CardInfo getRandomFullName() {
        return new CardInfo("4444444444444441", generateRandomMonth(), generateFutureYear(), generateFullName(locale), generateValidCvc());
    }

    public static CardInfo getRandomRussianName() {
        return new CardInfo("4444444444444441", generateRandomMonth(), generateFutureYear(), generateName("ru"), generateValidCvc());
    }

    public static CardInfo getRandomNumericForName() {
        return new CardInfo("4444444444444441", generateRandomMonth(), generateFutureYear(), generateValidCardNumber(), generateValidCvc());
    }

    public static CardInfo getRandomInvalidCvc() {
        return new CardInfo("4444444444444441", generateRandomMonth(), generateFutureYear(), generateName(locale), generateInvalidCvc());
    }

    public static CardInfo getRandomInvalidCvcOneSymbol() {
        return new CardInfo("4444444444444441", generateRandomMonth(), generateFutureYear(), generateName(locale), generateInvalidYear());
    }

    public static CardInfo getOwnerEmptyFieldCard() {
        return new CardInfo("4444444444444441", generateRandomMonth(), generateFutureYear(), "", generateValidCvc());
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String generateFullName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().fullName();
    }

    public static String generateValidCardNumber() {
        Faker faker = new Faker();
        return faker.numerify("################");
    }

    public static String generateInvalidCardNumber() {
        Faker faker = new Faker();
        return faker.numerify("###############");
    }

    public static String generateRandomMonth() {
        int month = (int)((Math.random() * 12) + 1);;
        return String.format("%02d", month);
    }

    public static String generatePastMonth() {
        LocalDate currentDate = LocalDate.now();
        int month = currentDate.getMonthValue() - 1;
        return String.format("%02d", month);
    }

    public static String generateInvalidMonth() {
        Faker faker = new Faker();
        return faker.numerify("#");
    }

    public static String generateCurrentYear() {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear() - 2000;
        return Integer.toString(year);
    }

    public static String generateFutureYear() {
        Random random = new Random();
        int i = random.nextInt(5) + 1;
        LocalDate futureDate = LocalDate.now().plusYears(i);
        int year = futureDate.getYear() - 2000;
        return Integer.toString(year);
    }

    public static String generatePastYear() {
        int pastYear = (int)((Math.random() * 22) + 1);;
        return String.format("%02d", pastYear);
    }

    public static String generateInvalidYear() {
        Faker faker = new Faker();
        return faker.numerify("#");
    }

    public static String generateValidCvc() {
        Faker faker = new Faker();
        return faker.numerify("###");
    }

    public static String generateInvalidCvc() {
        Faker faker = new Faker();
        return faker.numerify("##");
    }

    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String cardHolder;
        String cvc;
    }
}
