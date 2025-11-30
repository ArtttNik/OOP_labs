package org.example.dictionary;

import java.util.*;

public class Translator {
    private final Map<String, String> sortedDictionary;

    public Translator(Map<String, String> dictionary) {
        List<String> sortedKeys = new ArrayList<>(dictionary.keySet());
        sortedKeys.sort((a, b) -> Integer.compare(b.length(), a.length()));

        this.sortedDictionary = new LinkedHashMap<>();
        for (String key : sortedKeys) {
            this.sortedDictionary.put(key, dictionary.get(key));
        }
    }

    public String translateText(String text) {
        if (text == null || text.isEmpty()) return text;

        StringBuilder result = new StringBuilder();
        int i = 0;

        while (i < text.length()) {
            char c = text.charAt(i);
            if (!Character.isLetter(c)) {
                result.append(c);
                i++;
                continue;
            }

            boolean matchFound = false;

            for (Map.Entry<String, String> entry : sortedDictionary.entrySet()) {
                String key = entry.getKey();
                String translation = entry.getValue();
                int keyLength = key.length();
                int endIndex = i + keyLength;

                if (i > 0 && Character.isLetter(text.charAt(i - 1)))
                    continue;

                if (endIndex > text.length())
                    continue;

                String candidate = text.substring(i, endIndex);
                if (!candidate.equalsIgnoreCase(key))
                    continue;

                if (endIndex < text.length() && Character.isLetter(text.charAt(endIndex))) continue;

                String translated = applyOriginalCase(candidate, translation);
                result.append(translated);
                i += keyLength;
                matchFound = true;
                break;
            }

            if (!matchFound) {
                int j = i;
                while (j < text.length() && Character.isLetter(text.charAt(j))) {
                    j++;
                }
                result.append(text, i, j);
                i = j;
            }
        }

        return result.toString();
    }

    private String applyOriginalCase(String original, String translation) {
        if (original.isEmpty())
            return translation;

        if (original.equals(original.toUpperCase())) {
            return translation.toUpperCase();
        } else if (Character.isUpperCase(original.charAt(0))) {
            return Character.toUpperCase(translation.charAt(0)) +
                    (translation.length() > 1 ? translation.substring(1).toLowerCase() : "");
        }

        return translation.toLowerCase();
    }
}