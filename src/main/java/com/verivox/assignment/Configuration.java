package com.verivox.assignment;

import static java.lang.System.getenv;

// Set As system property or env variable
public enum Configuration {
    BROWSER("BROWSER", "firefox"),
    TEST_ENV("URL", "https://www.verivox.de"),
    OS("OS","linux"),
    IMPLICIT_WAIT("WAIT_TIME", "20");

    private final String value;

    Configuration(String mode, String defaultValue) {
        if (getenv(mode) != null)
            this.value = getenv(mode);
        else
            this.value = defaultValue;
    }

    public String getValue() {
        return this.value;
    }
}
