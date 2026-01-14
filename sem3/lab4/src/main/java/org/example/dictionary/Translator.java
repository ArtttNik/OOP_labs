package org.example.dictionary;

import java.util.Map;
import java.util.Locale;

public record Translator(Map<String, String> dictionary) {

    public Translator {
        if (dictionary == null) {
            throw new IllegalArgumentException("Dictionary is null");
        }
    }

    public String translateText(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        StringBuilder result = new StringBuilder();
        int i = 0;

        while (i < text.length()) {

            if (!isWordChar(text.charAt(i))) {
                result.append(text.charAt(i));
                i++;
                continue;
            }

            int matchEnd = findBestMatch(text, i);

            if (matchEnd > i) {
                String original = text.substring(i, matchEnd);
                String translation = dictionary.get(original.toLowerCase(Locale.ROOT));

                result.append(applyOriginalCase(original, translation));
                i = matchEnd;
            } else {
                int wordEnd = findWordEnd(text, i);
                result.append(text, i, wordEnd);
                i = wordEnd;
            }
        }

        return result.toString();
    }

    private boolean isWordChar(char c) {
        return Character.isLetter(c) || c == '-';
    }

    private int findWordEnd(String text, int start) {
        int i = start;
        while (i < text.length() && isWordChar(text.charAt(i))) {
            i++;
        }
        return i;
    }

    private int findBestMatch(String text, int start) {

        if (start > 0 && isWordChar(text.charAt(start - 1))) {
            return start;
        }

        int end = start;

        while (end < text.length()) {
            char c = text.charAt(end);
            if (isWordChar(c) || c == ' ') {
                end++;
            } else {
                break;
            }
        }

        while (end > start && text.charAt(end - 1) == ' ') {
            end--;
        }

        for (int i = end; i > start; i--) {
            if (i < text.length() && isWordChar(text.charAt(i))) {
                continue;
            }

            String candidate = text.substring(start, i).toLowerCase(Locale.ROOT);
            if (dictionary.containsKey(candidate)) {
                return i;
            }
        }

        return start;
    }

    private String applyOriginalCase(String original, String translation) {
        if (translation == null || translation.isEmpty()) {
            return translation;
        }

        if (Character.isUpperCase(original.charAt(0))) {
            return Character.toUpperCase(translation.charAt(0)) + translation.substring(1);
        }

        return translation;
    }
}
