package com.zebsoft.parser;

import java.util.HashMap;
import java.util.Map;

public final class ArgParser {

    private ArgParser() {
    }

    /**
     * Parses command-line arguments in the form of options and values.
     * Options are expected to start with "--", followed by their respective values.
     * If no value is provided, an empty string is used.
     *
     * @param args the array of command-line arguments
     * @return a map of options and their corresponding values
     * @throws IllegalArgumentException if an unexpected argument is encountered
     */
    public static Map<String, String> parse(String... args) {
        Map<String, String> options = new HashMap<>();
        String currentOption = null;
        for (int i = 1; i < args.length; i++) {
            String actual = args[i];
            if (actual.startsWith("--")) {
                currentOption = actual.substring(2);
                options.put(currentOption, "");
            } else {
                if (currentOption != null) {
                    options.put(currentOption, actual);
                    currentOption = null;
                } else {
                    throw new IllegalArgumentException("Error: unexpected argument " + actual);
                }
            }
        }
        return options;
    }
}
