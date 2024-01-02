package com.shahinnazarov.noteapp.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Map;

class WordCounterUtilsTest {

    @Test
    void testWithLongText() {
        final String content = """
                Lorem ipsum dolor sit amet, consectetur adipiscing elit,
                 sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                 Morbi enim nunc faucibus a pellentesque sit amet porttitor.
                 Lorem ipsum dolor sit amet consectetur adipiscing elit ut aliquam.
                 Egestas tellus rutrum tellus pellentesque eu tincidunt tortor aliquam nulla.
                 Amet nisl suscipit adipiscing bibendum est ultricies.
                 A diam sollicitudin tempor id eu nisl nunc mi. Eget sit amet tellus cras adipiscing enim.
                 Tincidunt id aliquet risus feugiat in ante metus dictum at.
                 Urna neque viverra justo nec ultrices dui sapien eget.
                 Ultricies integer quis auctor elit sed. Consectetur a erat nam at lectus urna duis.
                 Sed viverra tellus in hac habitasse platea.
                 Sem integer vitae justo eget magna fermentum iaculis eu non.
                 Massa tincidunt dui ut ornare lectus sit amet est.
                 Orci porta non pulvinar neque laoreet suspendisse interdum.
                """;
        final Map<String, Integer> stats = WordCounterUtils.calculate(content);

        final InputStream expectedJson = getClass().getClassLoader()
                .getResourceAsStream("word-stats-long-content.json");
        final Map<String, Integer> expectedStats = JsonUtils.asObject(expectedJson);

        Assertions.assertEquals(expectedStats, stats);
    }
}
