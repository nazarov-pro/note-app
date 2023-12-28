package com.shahinnazarov.noteapp.utils;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class WordCounterUtils {

    public static Map<String, Integer> calculate(@NonNull final String content) {
        final Map<String, Integer> result = new HashMap<>();
        int head = -1;
        for (int i = 0; i < content.length() - 1; i++) {
            if (head == -1 && Character.isLetter(content.charAt(i))) {
                head = i;
            } else if (!Character.isLetter(content.charAt(i + 1))) {
                final String word = content.substring(0, i + 1);
                result.merge(word, 1, Integer::sum);
                head = ++i +1;
            }
        }

        if (head < content.length()) {
            result.merge(content.substring(head), 1, Integer::sum);
        }
        return result;
    }
}
