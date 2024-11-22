import java.util.*;
public class Phonebook {
    private Map<String, List<String>> phoneBook;

    public Phonebook() {
        this.phoneBook = new HashMap<>();
    }

    // Метод для добавления записи
    public void add(String surname, String phoneNumber) {
        List<String> numbers = phoneBook.get(surname);
        if (numbers == null) {
            numbers = new ArrayList<>();
            phoneBook.put(surname, numbers);
        }
        numbers.add(phoneNumber);
    }

    // Получить номера по фамилии
    public List<String> get(String surname) {
        List<String> numbers = phoneBook.get(surname);
        if (numbers == null) {
            return new ArrayList<>();
        }
        return numbers;
    }


    public static void main(String[] args) {
        Phonebook phoneBook = new Phonebook();

        phoneBook.add("Abramovich", "+375441234560");
        phoneBook.add("Kalinina", "+375446543219");
        phoneBook.add("Ivanova", "+375447890128");
        phoneBook.add("Abramovich", "+375441345678");

        // Поиск номеров по фамилии
        System.out.println("Abramovich's numbers: " + phoneBook.get("Abramovich"));
        System.out.println("Kalinina's numbers: " + phoneBook.get("Kalinina"));
        System.out.println("Ivanova's numbers: " + phoneBook.get("Ivanova"));
        System.out.println("Nonexistent's numbers: " + phoneBook.get("Nonexistent"));
    }
}