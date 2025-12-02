package org.example.dictionary;

import org.example.exceptions.FileReadException;
import org.example.exceptions.InvalidFileFormatException;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

public class DictionaryLoader {
    public Map<String, String> load(Path path) throws FileReadException, InvalidFileFormatException {
        if (!Files.exists(path))
            throw new FileReadException("File not found: " + path.toAbsolutePath(), null);

        if (!Files.isRegularFile(path))
            throw new FileReadException("Not a file: " + path.toAbsolutePath(), null);

        if (!Files.isReadable(path))
            throw new FileReadException("Access denied: " + path.toAbsolutePath(), null);

        Map<String, String> dict = new TreeMap<>(
                (a, b) -> {
                    int lenCmp = Integer.compare(b.length(), a.length());

                    if  (lenCmp != 0)
                        return lenCmp;
                    else {
                        return a.compareTo(b);
                    }
                }
        );

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            int lineNum = 0;

            while ((line = reader.readLine()) != null) {
                lineNum++;
                line = line.trim();
                if (line.isEmpty())
                    continue;

                int sep = line.indexOf('|');
                if (sep < 0)
                    throw new InvalidFileFormatException("Line " + lineNum + ": missing '|' separator");

                String engWord = line.substring(0, sep).trim();
                String rusWord = line.substring(sep + 1).trim();

                if (engWord.isEmpty())
                    throw new InvalidFileFormatException("Line " + lineNum + ": empty word");

                if (rusWord.isEmpty())
                    throw new InvalidFileFormatException("Line " + lineNum + ": empty translation");

                dict.put(engWord.toLowerCase(), rusWord);
            }
        } catch (java.io.IOException e) {
            throw new FileReadException("Read error: " + path, e);
        }

        return dict;
    }
}
