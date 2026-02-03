package org.example.api.auth;

import org.example.api.config.ConfigManager;

public class AuthFactory {
    public static AuthStrategy getStrategy(){
        String type = ConfigManager.INSTANCE.get("auth.type");

        if (type == null || type.isBlank()) {
            return new NoAuthStrategy(); // safe fallback
        }

        switch (type.toUpperCase()) {
            case "BEARER":
                return new BearerTokenStrategy();
            case "NONE":
                return new NoAuthStrategy();
            default:
                throw new RuntimeException("Unsupported auth type: " + type);
        }
    }
}
