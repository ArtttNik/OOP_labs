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

public class DictionaryLoader {
    public Map<String, String> load(Path path) throws FileReadException, InvalidFileFormatException {
        Map<String, String> dict = new HashMap<>();

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            int lineNum = 0;

            while ((line = reader.readLine()) != null) {
                lineNum++;
                line = line.trim();
                if (line.isEmpty()) continue;

                int sep = line.indexOf('|');
                if (sep < 0) {
                    throw new InvalidFileFormatException("Line " + lineNum + ": missing '|' separator");
                }

                String left = line.substring(0, sep).trim();
                String right = line.substring(sep + 1).trim();

                if (left.isEmpty() || right.isEmpty()) {
                    throw new InvalidFileFormatException("Line " + lineNum + ": empty left or right part");
                }

                dict.put(left.toLowerCase(), right);
            }

        } catch (IOException e) {
            throw new FileReadException("Failed to read dictionary file: " + path, e);
        }

        return dict;
    }
}