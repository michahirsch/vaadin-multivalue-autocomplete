package com.bosch.example.rsql;

public class DefaultSuggestContext implements SuggestContext {

    private final boolean syntaxError;

    private final TokenSuggestionContext syntaxErrorContext;
    private final TokenSuggestionContext cursorContext;

    public DefaultSuggestContext(final boolean syntaxError, final TokenSuggestionContext syntaxErrorContext,
            final TokenSuggestionContext cursorContext) {
        this.syntaxError = syntaxError;
        this.syntaxErrorContext = syntaxErrorContext;
        this.cursorContext = cursorContext;
    }

    @Override
    public boolean hasSyntaxError() {
        return syntaxError;
    }

    @Override
    public TokenSuggestionContext getSyntaxErrorContext() {
        return syntaxErrorContext;
    }

    @Override
    public TokenSuggestionContext getCursorContext() {
        return cursorContext;
    }

}
