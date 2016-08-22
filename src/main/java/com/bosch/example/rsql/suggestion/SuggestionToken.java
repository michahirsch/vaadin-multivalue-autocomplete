package com.bosch.example.rsql.suggestion;

import org.antlr.v4.runtime.Token;

public class SuggestionToken {

    private final Token token;
    private final int expectedTokenType;

    public SuggestionToken(final Token token, final int expectedTokenType) {
        this.token = token;
        this.expectedTokenType = expectedTokenType;
    }

    public Token getToken() {
        return token;
    }

    public int getExpectedTokenType() {
        return expectedTokenType;
    }
}
