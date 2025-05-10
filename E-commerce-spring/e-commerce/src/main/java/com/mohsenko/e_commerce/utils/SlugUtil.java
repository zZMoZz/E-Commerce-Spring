package com.mohsenko.e_commerce.utils;

import java.text.Normalizer;

public class SlugUtil {
    public static String toSlug(String input) {
        if (input == null || input.isBlank()) {
            // I don't want this message appear to the normal user
            throw new RuntimeException("There is a problem with the slug");
        }

        // trim the input
        input = input.trim();

        // convert characters with accents into their base characters (Café -> Cafe + accent)
        input = Normalizer.normalize(input, Normalizer.Form.NFD);

        // remove diacritic marks (accents, tildes, etc.)
        input = input.replaceAll("\\p{M}", "");

        // convert to lowercase
        input = input.toLowerCase();

        // remove special characters
        input = input.replaceAll("[^a-z0-9\\s-]", "");

        // remove multiple spaces into single hyphen
        input = input.replaceAll("\\s+", "-");

        // remove hyphens at the beginning and end
        input = input.replaceAll("^-+|-+$", "");

        return input;
    }
}
