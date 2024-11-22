import java.util.*;


public class Lesson13 {
    public static void main(String[] args) {

        // Создаем массив слов
        String[] words = {
                "роза", "фиалка", "циния", "жасмин", "ландыш",
                "лютик", "сирень", "роза", "роза", "ландыш",
                "пион", "гвоздика", "пион", "ромашка", "сирень", "фиалка", "фиалка", "фиалка"
        };

        // Найти и вывести список уникальных слов
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));
        System.out.println("Уникальные слова: " + uniqueWords);

        // Подсчитать сколько раз встречается каждое слово
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            Integer count = wordCount.get(word);
            if (count == null) {
                wordCount.put(word, 1);
            } else {
                wordCount.put(word, count + 1);
            }
        }

        // Вывод сколько раз встречается каждое слово
        System.out.println("Сколько раз встречается каждое слово:");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
