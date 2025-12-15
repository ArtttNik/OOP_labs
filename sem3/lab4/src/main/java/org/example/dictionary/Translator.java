package org.example.dictionary;

import java.util.Map;

public record Translator(Map<String, String> dictionary) {

    public String translateText(String text) {
        if (text == null || text.isEmpty())
            return text;

        StringBuilder result = new StringBuilder();
        int i = 0;

        while (i < text.length()) {
            char c = text.charAt(i);

            if (!Character.isLetter(c)) {
                result.append(c);
                i++;

                continue;
            }

            int match = findBestMatch(text, i);

            if (match > i) {
                String original = text.substring(i, match);
                String translation = dictionary.get(original.toLowerCase());

                result.append(applyOriginalCase(original, translation));
                i = match;
            } else {
                int j = i;
                while (j < text.length() && Character.isLetter(text.charAt(j)))
                    j++;

                result.append(text, i, j);
                i = j;
            }
        }

        return result.toString();
    }

    private int findBestMatch(String text, int start) {

        if (start > 0 && Character.isLetter(text.charAt(start - 1)))
            return start;

        int end = start;

        while (end < text.length()) {
            char c = text.charAt(end);
            if (Character.isLetter(c) || c == ' ')
                end++;
            else
                break;
        }

        while (end > start && text.charAt(end - 1) == ' ')
            end--;

        for (int i = end; i > start; i--) {
            if (i < text.length() && Character.isLetter(text.charAt(i)))
                continue;

            String candidate = text.substring(start, i).toLowerCase();
            if (dictionary.containsKey(candidate))
                return i;
        }

        return start;
    }

    private String applyOriginalCase(String original, String translation) {
        if (translation == null || translation.isEmpty())
            return translation;

        if (Character.isUpperCase(original.charAt(0)))
            return Character.toUpperCase(translation.charAt(0)) + translation.substring(1);

        return translation;
    }
}
