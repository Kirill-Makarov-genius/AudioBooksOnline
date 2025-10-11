package com.example.AudioBooksOnline.services;

import java.util.HashMap;
import java.util.Map;

public class Transliterator {
    private static final Map<Character, String> map = new HashMap<>();

    static {
        map.put('А', "A"); map.put('а', "a");
        map.put('Б', "B"); map.put('б', "b");
        map.put('В', "V"); map.put('в', "v");
        map.put('Г', "G"); map.put('г', "g");
        map.put('Д', "D"); map.put('д', "d");
        map.put('Е', "E"); map.put('е', "e");
        map.put('Ё', "Yo"); map.put('ё', "yo");
        map.put('Ж', "Zh"); map.put('ж', "zh");
        map.put('З', "Z"); map.put('з', "z");
        map.put('И', "I"); map.put('и', "i");
        map.put('Й', "Y"); map.put('й', "y");
        map.put('К', "K"); map.put('к', "k");
        map.put('Л', "L"); map.put('л', "l");
        map.put('М', "M"); map.put('м', "m");
        map.put('Н', "N"); map.put('н', "n");
        map.put('О', "O"); map.put('о', "o");
        map.put('П', "P"); map.put('п', "p");
        map.put('Р', "R"); map.put('р', "r");
        map.put('С', "S"); map.put('с', "s");
        map.put('Т', "T"); map.put('т', "t");
        map.put('У', "U"); map.put('у', "u");
        map.put('Ф', "F"); map.put('ф', "f");
        map.put('Х', "Kh"); map.put('х', "kh");
        map.put('Ц', "Ts"); map.put('ц', "ts");
        map.put('Ч', "Ch"); map.put('ч', "ch");
        map.put('Ш', "Sh"); map.put('ш', "sh");
        map.put('Щ', "Shch"); map.put('щ', "shch");
        map.put('Ы', "Y"); map.put('ы', "y");
        map.put('Э', "E"); map.put('э', "e");
        map.put('Ю', "Yu"); map.put('ю', "yu");
        map.put('Я', "Ya"); map.put('я', "ya");
        map.put('Ь', ""); map.put('ь', "");
        map.put('Ъ', ""); map.put('ъ', "");
    }

    public static String transliterate(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append(map.getOrDefault(c, String.valueOf(c)));
        }
        return sb.toString();
    }
}