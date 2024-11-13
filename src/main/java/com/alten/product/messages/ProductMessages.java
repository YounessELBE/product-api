package com.alten.product.messages;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@Component
public class ProductMessages {

    private String RessourceName;

    public ProductMessages() {
        this.RessourceName = "messages"; // messages.properties
    }

    public String getMessage(String key, Object... params) {
        return this.getMessage(key, this.RessourceName, params);
    }

    private String getMessage(String key, String ressourceName, Object... params) {
        if (!StringUtils.hasText(ressourceName)) {
            return key;

        } else {
            String text = null;
            try {
                ResourceBundle messages = ResourceBundle.getBundle(ressourceName);
                if (messages != null) {
                    text = messages.getString(key);
                }
            } catch (MissingResourceException exception) {
                return key;
            }

            String message = text;
            if (StringUtils.hasText(text) && params != null && params.length > 0) {
                message = MessageFormat.format(text, params);
            }

            return message;
        }
    }
}
