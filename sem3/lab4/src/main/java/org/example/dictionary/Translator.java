package org.example.dictionary;

import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Translator {
    private final Map<String, String> dictionary;
    private final int maxPhraseLength;

    public Translator(Map<String, String> dictionary) {
        this.dictionary = new HashMap<>();
        int maxLength = 1;

        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            String key = entry.getKey().toLowerCase();
            this.dictionary.put(key, entry.getValue());
            int wordCount = key.trim().isEmpty() ? 0 : key.split("\\s+").length;
            maxLength = Math.max(maxLength, wordCount);
        }
        this.maxPhraseLength = maxLength;
    }

    public String translateText(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        String[] tokens = text.split("(?<=\\s)|(?=\\s)");
        StringBuilder result = new StringBuilder();
        int pos = 0;

        while (pos < tokens.length) {
            String token = tokens[pos];

            if (token.trim().isEmpty()) {
                result.append(token);
                pos++;
                continue;
            }

            String word = token.replaceAll("^\\P{L}+|\\P{L}+$", "");
            if (word.isEmpty()) {
                result.append(token);
                pos++;
                continue;
            }

            String translation = findTranslation(tokens, pos);
            if (translation != null) {
                result.append(translation);
                pos += countWords(translation);
            } else {
                result.append(token);
                pos++;
            }
        }

        return result.toString();
    }

    private String findTranslation(String[] tokens, int start) {
        for (int length = maxPhraseLength; length >= 1; length--) {
            String phrase = buildPhrase(tokens, start, length);
            if (phrase != null) {
                String translation = dictionary.get(phrase);
                if (translation != null) {
                    return formatTranslation(tokens, start, start + length - 1, translation);
                }
            }
        }
        return null;
    }

    private String buildPhrase(String[] tokens, int start, int length) {
        List<String> words = new ArrayList<>();
        int pos = start;
        int collected = 0;

        while (pos < tokens.length && collected < length) {
            String token = tokens[pos];
            if (!token.trim().isEmpty()) {
                String word = token.replaceAll("^\\P{L}+|\\P{L}+$", "");
                if (word.isEmpty()) {
                    return null;
                }
                words.add(word.toLowerCase());
                collected++;
            }
            pos++;
        }

        return words.size() == length ? String.join(" ", words) : null;
    }

    private String formatTranslation(String[] tokens, int start, int end, String translation) {
        String first = tokens[start];
        String last = tokens[end];

        String prefix = extractPrefix(first);
        String suffix = extractSuffix(last);

        return prefix + translation + suffix;
    }

    private String extractPrefix(String token) {
        Matcher m = Pattern.compile("^(\\P{L}+)").matcher(token);
        return m.find() ? m.group(1) : "";
    }

    private String extractSuffix(String token) {
        Matcher m = Pattern.compile("(\\P{L}+)$").matcher(token);
        return m.find() ? m.group(1) : "";
    }

    private int countWords(String text) {
        String trimmed = text.trim();
        return trimmed.isEmpty() ? 0 : trimmed.split("\\s+").length;
    }
}