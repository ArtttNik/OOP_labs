package org.example.dictionary;

import org.example.exceptions.FileReadException;
import org.example.exceptions.InvalidFileFormatException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DictionaryLoader {

    private static final Pattern DICT_LINE_PATTERN = Pattern.compile(
            "^([a-zA-Z]+(?:[\\s-][a-zA-Z]+)*)\\s*\\|\\s*([а-яА-ЯёЁ]+(?:[\\s-][а-яА-ЯёЁ]+)*)$"
    );

    public Map<String, String> load(Path path) throws FileReadException, InvalidFileFormatException {
        if (path == null) {
            throw new IllegalArgumentException("Path is null");
        }
        Map<String, String> dictionary = new HashMap<>();

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            int lineNum = 0;

            while ((line = reader.readLine()) != null) {
                lineNum++;

                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                Matcher matcher = DICT_LINE_PATTERN.matcher(line);
                if (!matcher.matches()) {
                    throw new InvalidFileFormatException("Line " + lineNum + ": invalid dictionary format. " +
                            "String: " + line);
                }

                String key = matcher.group(1).toLowerCase();
                String translation = matcher.group(2);

                if (dictionary.containsKey(key)) {
                    System.err.println("\nWARNING: Line " + lineNum + ": duplicate entry rewritten\n");
                }
                dictionary.put(key, translation);
            }

        } catch (IOException e) {
            throw new FileReadException("Read error: " + path.toAbsolutePath(), e);
        }

        return dictionary;
    }
}
