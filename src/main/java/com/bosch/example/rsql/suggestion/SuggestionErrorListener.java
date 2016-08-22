package com.bosch.example.rsql.suggestion;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.IntervalSet;

import rsql.RsqlParser;

public class SuggestionErrorListener extends BaseErrorListener {

    private List<String> currentSuggestion = new ArrayList<String>();
    private boolean errorOccurred;

    @Override
    public void syntaxError(final Recognizer<?, ?> arg0, final Object arg1, final int arg2, final int arg3,
            final String arg4, final RecognitionException arg5) {
        final RsqlParser parser = (RsqlParser) arg0;
        final IntervalSet expectedTokensWithinCurrentRule = parser.getExpectedTokensWithinCurrentRule();
        final String symbolicName = parser.getVocabulary()
                .getSymbolicName(expectedTokensWithinCurrentRule.getMinElement());
        currentSuggestion = SuggestionMap.getSuggestions(symbolicName);
        errorOccurred = true;
    }

    public List<String> getCurrentSuggestion() {
        return currentSuggestion;
    }

    public void setCurrentSuggestion(final List<String> currentSuggestion) {
        this.currentSuggestion = currentSuggestion;
    }

    public boolean isErrorOccurred() {
        return errorOccurred;
    }
}