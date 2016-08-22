package com.bosch.example.rsql.suggestion;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import rsql.RsqlParser;

public class SuggestionErrorListener extends BaseErrorListener {

    private SuggestionToken errorToken;
    private boolean errorOccurred;

    @Override
    public void syntaxError(final Recognizer<?, ?> arg0, final Object arg1, final int arg2, final int arg3,
            final String arg4, final RecognitionException arg5) {
        final RsqlParser parser = (RsqlParser) arg0;
        final CommonToken token = (CommonToken) arg1;

        errorToken = new SuggestionToken(token, parser.getExpectedTokensWithinCurrentRule().getMinElement());
        errorOccurred = true;
    }

    public SuggestionToken getErrorToken() {
        return errorToken;
    }

    public boolean isErrorOccurred() {
        return errorOccurred;
    }
}