package se.pingstteknik.propresenter.stagedisplayviewer.config;

import static java.lang.Integer.parseInt;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import se.pingstteknik.propresenter.stagedisplayviewer.util.Logger;
import se.pingstteknik.propresenter.stagedisplayviewer.util.LoggerFactory;

/**
 * @author Daniel Kihlgren
 * @version 1.3.0
 * @since 1.0.0
 */
public enum Property {
    HOST("localhost"),
    PORT("45678"),
    PASSWORD("password"),
    DIVIDER(" "),
    OUTPUT_SCREEN("1"),
    FONT_FAMILY("Droid serif"),
    MIN_FONT_SIZE("30"),
    MAX_FONT_SIZE("60"),
    MARGIN_BOTTOM("10"),
    RESPONSE_TIME_MILLIS("50"),
    OUTPUT_WIDTH_PERCENTAGE("90"),
    TEXT_TRANSLATOR_ACTIVE("true"),
    PRESERVE_TWO_LINES("false"),
    MIDI("false"),
    REMOVE_LINES_AFTER_EMPTY_LINE("false"),
	FADE_TIME("0"); // Length of fade transition in milliseconds.

    private static final Logger log = LoggerFactory.getLogger(Property.class);
    private static final Properties properties = new Properties();
    private static final String PROPERTIES_FILE_NAME = "config.properties";

    private final String defaultValue;

    Property(String s) {
        defaultValue = s;
    }

    public String toString() {
        return properties.getProperty(name(), defaultValue);
    }

    public int toInt() {
        return parseInt(toString());
    }

    public boolean isTrue() {
        return Boolean.parseBoolean(toString());
    }

    public static void loadProperties() {
        try (FileInputStream file = new FileInputStream(PROPERTIES_FILE_NAME)) {
            properties.load(file);
        } catch (IOException e) {
            log.warn("Properties file not found, using default values");
        }
    }
}
