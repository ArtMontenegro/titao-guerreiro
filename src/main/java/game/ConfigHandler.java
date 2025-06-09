package game;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigHandler {

    private static Properties config = new Properties();

    public static void loadConfig(String path) {
        try (InputStream input = new FileInputStream(path)) {
            config.load(input);
        } catch (IOException ex) {
            System.err.println("Failed to load config file: " + ex.getMessage());
        }
    }

    public static String getConfigValue(String key, String defaultValue) {
        return config.getProperty(key, defaultValue);
    }

    public static int getConfigInt(String key, int defaultValue) {
        String value = config.getProperty(key);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.err.println("Invalid integer for key " + key);
            }
        }
        return defaultValue;
    }

    public static boolean getConfigBoolean(String key, boolean defaultValue) {
        String value = config.getProperty(key);
        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        return defaultValue;
    }
}