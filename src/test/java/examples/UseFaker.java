package examples;

import com.github.javafaker.Faker;

public class UseFaker {
    Faker faker = new Faker();
    String name = faker.name().fullName(); // Генерация случайного полного имени
    String address = faker.address().streetAddress(); // Генерация случайного адреса

    // Генерируем случайный email
    String email = faker.internet().emailAddress();
}
