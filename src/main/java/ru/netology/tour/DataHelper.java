package ru.netology.tour;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private static final Faker faker = new Faker(new Locale("en"));

    private DataHelper() {}

    public static CardInfo getApprovedCard() {
        return new CardInfo("4444 4444 4444 4441", getMonth(), getYear(), getName(), getCVC());
    }

    public static CardInfo getDeclinedCard() {
        return new CardInfo("4444 4444 4444 4442", getMonth(), getYear(), getName(), getCVC());
    }

    public static CardInfo getInvalidCard() {
        return new CardInfo("0000 0000 0000 0000", "13", "00", " ", "000");
    }

    public static CardInfo getEmptyCard() {
        return new CardInfo(" ", " ", " ", " ", " ");
    }

    private static String getMonth() {
        return String.format("%02d", faker.number().numberBetween(1, 12));
    }

    private static String getYear() {
        return String.valueOf(faker.number().numberBetween(25, 29));
    }

    private static String getName() {
        return faker.name().fullName();
    }

    private static String getCVC() {
        return String.format("%03d", faker.number().numberBetween(100, 999));
    }

//    @Value
//    public static class CardInfo {
//        String cardNumber;
//        String month;
//        String year;
//        String holder;
//        String cvc;
//    }
}

