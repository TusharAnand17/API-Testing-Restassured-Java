package org.example.api.config;

public class EnvironmentResolver {
    private static final String ENV_PROPERTY = "env";

    public static Environment resolve() {
        String env = System.getProperty(ENV_PROPERTY, "DEV").toUpperCase();
        return Environment.valueOf(env);
    }
}
