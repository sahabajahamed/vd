package com.Weaversweb.utils;

import com.github.javafaker.Faker;

public class TestDataGenerator {


    private static final Faker faker = new Faker();

    // Private constructor to prevent instantiation
    private TestDataGenerator() {}

    public static String firstName() {
        return faker.name().firstName();
    }

    public static String lastName() {
        return faker.name().lastName();
    }

    public static String fullName() {
        return faker.name().fullName();
    }

    public static String email() {
        return faker.internet().emailAddress();
    }

    public static String phone() {
        return faker.phoneNumber().cellPhone().replaceAll("[^0-9]", "");
    }

    public static String address() {
        return faker.address().fullAddress();
    }

    public static String city() {
        return faker.address().city();
    }

    public static String state() {
        return faker.address().state();
    }

    public static String zipCode() {
        return faker.address().zipCode();
    }

    public static String company() {
        return faker.company().name();
    }

    public static String jobTitle() {
        return faker.job().title();
    }

    public static String password(int minLength, int maxLength) {
        return faker.internet().password(minLength, maxLength, true, true);
    }

    public static int number(int min, int max) {
        return faker.number().numberBetween(min, max);
    }

    public static String creditCardNumber() {
        return faker.finance().creditCard();
    }
    
}
