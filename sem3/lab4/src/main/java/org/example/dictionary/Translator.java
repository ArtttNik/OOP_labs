package org.example.dictionary;

import java.util.*;

public class Translator {
    private final Map<String, String> dictionary;
    private final List<String> sortedKeys;

    public Translator(Map<String, String> dictionary) {
        this.dictionary = new HashMap<>();
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            this.dictionary.put(entry.getKey().toLowerCase(), entry.getValue());
        }

        this.sortedKeys = new ArrayList<>(this.dictionary.keySet());
        sortedKeys.sort((a, b) -> {
            int lenDiff = b.length() - a.length();
            if (lenDiff != 0) return lenDiff;
            return b.split("\\s+").length - a.split("\\s+").length;
        });
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

            int j = i;
            while (j < text.length() && (Character.isLetter(text.charAt(j)) || Character.isWhitespace(text.charAt(j)))) {
                j++;
            }

            String candidate = text.substring(i, j);
            String bestMatch = null;
            String bestTranslation = null;

            for (String key : sortedKeys) {
                if (candidate.toLowerCase().startsWith(key)) {
                    if (key.length() < candidate.length()) {
                        char nextChar = candidate.charAt(key.length());
                        if (Character.isLetter(nextChar)) {
                            continue;
                        }
                    }
                    bestMatch = key;
                    bestTranslation = dictionary.get(key);
                    break;
                }
            }

            if (bestMatch != null) {
                String originalPhrase = text.substring(i, i + bestMatch.length());
                String translated = applyOriginalCase(originalPhrase, bestTranslation);
                result.append(translated);
                i += bestMatch.length();
            } else {
                result.append(c);
                i++;
            }
        }

        return result.toString();
    }

    private String applyOriginalCase(String original, String translation) {
        if (original.isEmpty()) return translation;
        if (Character.isUpperCase(original.charAt(0))) {
            if (translation.length() == 1) {
                return translation.toUpperCase();
            } else {
                return Character.toUpperCase(translation.charAt(0)) + translation.substring(1);
            }
        }
        return translation;
    }
}