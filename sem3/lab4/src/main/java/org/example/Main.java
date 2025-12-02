package org.example;

import org.example.dictionary.DictionaryLoader;
import org.example.dictionary.Translator;
import org.example.exceptions.FileReadException;
import org.example.exceptions.InvalidFileFormatException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("invalid args");
            System.exit(1);
        }

        Path dictPath = Paths.get(args[0]);
        Path inputPath = Paths.get(args[1]);

        try {
            DictionaryLoader loader = new DictionaryLoader();
            Map<String, String> dict = loader.load(dictPath);

            String inputText;
            try (BufferedReader reader = Files.newBufferedReader(inputPath, StandardCharsets.UTF_8)) {
                inputText = reader.lines().collect(Collectors.joining("\n"));
            }

            Translator translator = new Translator(dict);
            String result = translator.translateText(inputText);
            System.out.print(result);

        } catch (InvalidFileFormatException | FileReadException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Input file error: " + e.getMessage());
            System.exit(1);
        }
    }
}
