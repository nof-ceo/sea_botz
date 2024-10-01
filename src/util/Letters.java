package util;

import java.util.HashMap;
import java.util.Map;

public class Letters {
    public static final Map<Character, Integer> LETTERS = new HashMap<>();
    static {
        LETTERS.put('А', 0);
        LETTERS.put('Б', 1);
        LETTERS.put('В', 2);
        LETTERS.put('Г', 3);
        LETTERS.put('Д', 4);
        LETTERS.put('Е', 5);
        LETTERS.put('Ж', 6);
        LETTERS.put('З', 7);
        LETTERS.put('И', 8);
        LETTERS.put('К', 9);
    }
}
