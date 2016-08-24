package com.bosch.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bosch.example.rsql.SuggestContext;
import com.bosch.example.rsql.SuggestOracle;
import com.bosch.example.rsql.TokenSuggestionContext;
import com.bosch.example.rsql.suggestion.AntlrSuggestOracle;
import com.vaadin.spring.annotation.EnableVaadin;

@SpringBootApplication
@EnableVaadin
public class DemoAppApplication {

    public static void main(final String[] args) {
        SpringApplication.run(DemoAppApplication.class, args);
    }

    @Bean
    public SuggestOracle suggestOracle() {
        return new AntlrSuggestOracle();
    }

    private static final class DummySuggestOracle implements SuggestOracle {

        @Override
        public SuggestContext getSuggestion(final String query, final int cursorPos) {
            return new SuggestContext() {

                @Override
                public boolean hasSyntaxError() {
                    return false;
                }

                @Override
                public TokenSuggestionContext getSyntaxErrorContext() {
                    return null;
                }

                @Override
                public TokenSuggestionContext getCursorContext() {
                    return null;
                }
            };
        }

    }
}