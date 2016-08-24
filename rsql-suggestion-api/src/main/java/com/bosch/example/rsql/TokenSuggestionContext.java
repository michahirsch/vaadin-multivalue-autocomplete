package com.bosch.example.rsql;

import java.util.List;

public class TokenSuggestionContext {

    final int start;
    final int end;
    final List<String> suggestions;

    public TokenSuggestionContext(final int start, final int end, final List<String> suggestions) {
        this.start = start;
        this.end = end;
        this.suggestions = suggestions;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }
}
