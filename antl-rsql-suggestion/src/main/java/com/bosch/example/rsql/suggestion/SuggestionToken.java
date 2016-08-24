package com.bosch.example.rsql.suggestion;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.IntervalSet;

public class SuggestionToken {

    private final Token token;
    private final IntervalSet expectedTokenType;

    public SuggestionToken(final Token token, final IntervalSet intervalSet) {
        this.token = token;
        this.expectedTokenType = intervalSet;
    }

    public Token getToken() {
        return token;
    }

    public IntervalSet getExpectedTokenType() {
        return expectedTokenType;
    }
}
