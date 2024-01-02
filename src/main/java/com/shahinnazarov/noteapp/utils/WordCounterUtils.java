package com.shahinnazarov.noteapp.utils;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.*;
import java.util.stream.Collectors;

@UtilityClass
public class WordCounterUtils {

    public static Map<String, Integer> calculate(@NonNull final String content) {
        final Map<String, Integer> result = new HashMap<>();
        int head = -1;
        for (int i = 0; i < content.length() - 1; i++) {
            if (head == -1 && Character.isLetter(content.charAt(i))) {
                head = i;
            }

            if (!Character.isLetter(content.charAt(i + 1)) && head >= 0) {
                final String word = content.substring(head, i + 1);
                result.merge(word, 1, Integer::sum);
                head = -1;
            }
        }

        if (head >= 0) {
            result.merge(content.substring(head), 1, Integer::sum);
        }

        return result.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldVal, newVal) -> newVal,
                        LinkedHashMap::new
                ));
    }
}
