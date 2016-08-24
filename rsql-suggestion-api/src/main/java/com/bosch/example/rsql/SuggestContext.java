package com.bosch.example.rsql;

public interface SuggestContext {

    boolean hasSyntaxError();

    TokenSuggestionContext getSyntaxErrorContext();

    TokenSuggestionContext getCursorContext();

}
