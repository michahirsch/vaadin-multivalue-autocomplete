package com.bosch.example.rsql.suggestion;

import com.bosch.example.rsql.DefaultSuggestContext;
import com.bosch.example.rsql.SuggestContext;
import com.bosch.example.rsql.SuggestOracle;
import com.bosch.example.rsql.TokenSuggestionContext;
import com.bosch.example.rsql.suggestion.RsqlSuggestionHelper.SuggestionContext;

public class AntlrSuggestOracle implements SuggestOracle {

    @Override
    public SuggestContext getSuggestion(final String query, final int cursorPos) {
        final SuggestionContext parse = RsqlSuggestionHelper.parse(query, cursorPos);

        TokenSuggestionContext syntax = null;
        TokenSuggestionContext cursor = null;
        if (parse.getSyntaxErrorContext() != null) {
            syntax = new TokenSuggestionContext(parse.getSyntaxErrorContext().getTokenStart(),
                    parse.getSyntaxErrorContext().getTokenEnd(), parse.getSyntaxErrorContext().getSuggestions());
        }
        if (parse.getCursorPositionContext() != null) {
            cursor = new TokenSuggestionContext(parse.getCursorPositionContext().getTokenStart(),
                    parse.getCursorPositionContext().getTokenEnd(), parse.getCursorPositionContext().getSuggestions());
        }

        return new DefaultSuggestContext(parse.isSyntaxError(), syntax, cursor);
    }

}
