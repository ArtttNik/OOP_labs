package org.example;

import org.example.dictionary.DictionaryLoader;
import org.example.dictionary.Translator;
import org.example.exceptions.FileReadException;
import org.example.exceptions.InvalidFileFormatException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java -jar translator.jar <dictionary-file> <input-file>");
            System.exit(1);
        }

        Path dictPath = Paths.get(args[0]);
        Path inputPath = Paths.get(args[1]);

        try {
            DictionaryLoader loader = new DictionaryLoader();
            Map<String, String> dict = loader.load(dictPath);

            Translator translator = new Translator(dict);
            String inputText = Files.readString(inputPath, StandardCharsets.UTF_8);

            String result = translator.translateText(inputText);
            System.out.println(result);

        } catch (InvalidFileFormatException e) {
            System.err.println("Invalid dictionary format: " + e.getMessage());
            System.exit(1);
        } catch (FileReadException e) {
            System.err.println("Failed to read dictionary file: " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Failed to read input file: " + e.getMessage());
            System.exit(1);
        }
    }
}