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

        validateFile(path);

        Map<String, String> dictionary = new HashMap<>();

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {

            String line;
            int lineNum = 0;

            while ((line = reader.readLine()) != null) {
                lineNum++;

                line = line.trim();
                if (line.isEmpty())
                    continue;

                Matcher matcher = DICT_LINE_PATTERN.matcher(line);
                if (!matcher.matches()) {
                    throw new InvalidFileFormatException("Line " + lineNum + ": invalid dictionary format");
                }

                String source = matcher.group(1);
                String translation = matcher.group(2);

                String key = source.toLowerCase();

                if (dictionary.containsKey(key)) {
                    System.err.println("WARNING: Line " + lineNum + ": duplicate entry rewritten");
                }

                dictionary.put(key, translation);

            }

        } catch (IOException e) {
            throw new FileReadException("Read error: " + path.toAbsolutePath(), e);
        }

        return dictionary;
    }

    private void validateFile(Path path) throws FileReadException {
        if (path == null)
            throw new FileReadException("Path is null", null);

        if (!Files.exists(path))
            throw new FileReadException("File not found: " + path.toAbsolutePath(), null);

        if (!Files.isRegularFile(path))
            throw new FileReadException("Not a file: " + path.toAbsolutePath(), null);

        if (!Files.isReadable(path))
            throw new FileReadException("Access denied: " + path.toAbsolutePath(), null);
    }
}
