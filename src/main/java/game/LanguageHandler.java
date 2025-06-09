package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;

public class LanguageHandler {

    private static final String CONFIG_FILE = "config.properties";
    private static final Set<String> SUPPORTED_LANGS = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("en", "pt", "es")));
    private static String langCode = "en";
    private static ResourceBundle bundle;

    public static ResourceBundle loadLanguage(String[] args) {
        // Priority 1: config file
        Properties config = new Properties();
        File configFile = new File(CONFIG_FILE);

        if (configFile.exists()) {
            try (FileReader reader = new FileReader(configFile)) {
                config.load(reader);
                String savedLang = config.getProperty("language");
                if (savedLang != null && SUPPORTED_LANGS.contains(savedLang)) {
                    langCode = savedLang;
                }
            } catch (IOException e) {
                System.err.println("Error loading config file: " + e.getMessage());
            }
        } else {
            // Priority 2: command-line argument
            if (args.length > 0 && SUPPORTED_LANGS.contains(args[0].trim())) {
                langCode = args[0].trim();
            } else {
                // Priority 3: prompt user
                promptForLanguage();
            }
            // Save chosen language
            try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
                config.setProperty("language", langCode);
                config.store(writer, "Game Configuration");
            } catch (IOException e) {
                System.err.println("Error saving config file: " + e.getMessage());
            }
        }

        bundle = ResourceBundle.getBundle("Language", new Locale(langCode), new UTF8Control());
        return bundle;
    }

    private static void promptForLanguage() {
        BufferedReader langIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.println();
        System.out.println("Choose a language/ Escolhe uma língua/ Elige un idioma");
        System.out.println("English: en");
        System.out.println("Português: pt");
        System.out.println("Español: es");
        System.out.print("> ");

        try {
            String input = langIn.readLine();
            if (input != null && SUPPORTED_LANGS.contains(input.trim().toLowerCase())) {
                langCode = input.trim().toLowerCase();
            } else {
                System.out.println("Invalid code. Defaulting to English.");
                langCode = "en";
            }
        } catch (IOException e) {
            System.out.println("Error reading input. Defaulting to English.");
            langCode = "en";
        }
    }

    public static boolean isSupported(String code) {
        return SUPPORTED_LANGS.contains(code);
    }

    public static void setLanguage(String code) {
        if (!isSupported(code)) {
            code = "en";
        }
        langCode = code;

        // Update config
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            Properties config = new Properties();
            config.setProperty("language", langCode);
            config.store(writer, "Game Configuration");
        } catch (IOException e) {
            System.err.println("Error writing config: " + e.getMessage());
        }

        // Reload bundle
        bundle = ResourceBundle.getBundle("Language", new Locale(langCode), new UTF8Control());
    }

    public static ResourceBundle getBundle() {
        return bundle;
    }

    // Custom UTF-8 reader for .properties files
    public static class UTF8Control extends ResourceBundle.Control {
        @Override
        public ResourceBundle newBundle(String baseName, Locale locale, String format,
                ClassLoader loader, boolean reload) throws IOException {
            String bundleName = toBundleName(baseName, locale);
            String resourceName = toResourceName(bundleName, "properties");

            try (InputStream stream = loader.getResourceAsStream(resourceName)) {
                if (stream != null) {
                    try (BufferedReader reader = new BufferedReader(
                            new InputStreamReader(stream, StandardCharsets.UTF_8))) {
                        return new PropertyResourceBundle(reader);
                    }
                }
            }
            return null;
        }
    }
}