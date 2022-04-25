package lesson3;

import java.util.HashMap;
import java.util.Map;

public class Homework3Task1 {
    private static final String[] INPUT_DATA = {
            "ok",
            "hello",
            "join", "join",
            "volume",
            "value",
            "map",
            "ok", "ok", "ok",
            "hello",
            "enter",
            "map",
            "volume"
    };
    public static void main(String[] args) {
        Map<String, Integer> frequencyByWord = new HashMap<>();
        for (String word : INPUT_DATA) {
            Integer frequency = frequencyByWord.getOrDefault(word, 0);
            frequencyByWord.put(word, ++frequency);
        }
        for (Map.Entry<String, Integer> entry : frequencyByWord.entrySet()) {
            System.out.printf("Слово %s встречается %d раз %n", entry.getKey(), entry.getValue());
        }
    }
}
