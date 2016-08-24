package com.bosch.example.rsql.suggestion;

public enum CombinerSuggestion {

    AND("&&"), OR("||");

    private final String symbol;

    private CombinerSuggestion(final String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
