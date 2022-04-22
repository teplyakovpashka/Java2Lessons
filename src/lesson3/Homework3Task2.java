package lesson3;

import java.util.Set;

public class Homework3Task2 {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Makarov", "777-123");
        phoneBook.add("Petrov", "123-1");
        phoneBook.add("Petrov", "123-2");
        phoneBook.add("Ivanov", "456");
        phoneBook.add("Sidorov", "789");

        Set<String> surnames = phoneBook.allSurnames();
        for (String surname : surnames) {
            System.out.printf("Пользователь %s имеет номер(а) телефонов %s %n", surname, phoneBook.get(surname));
        }
    }
}
