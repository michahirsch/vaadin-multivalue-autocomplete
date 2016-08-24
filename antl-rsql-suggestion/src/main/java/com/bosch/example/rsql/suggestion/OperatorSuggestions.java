package com.bosch.example.rsql.suggestion;

public enum OperatorSuggestions {

    EQUAL("equal", "=="), NOTEQUAL("not equal", "!=");

    private final String opName;
    private final String opSymbol;

    private OperatorSuggestions(final String opName, final String opSymbol) {
        this.opName = opName;
        this.opSymbol = opSymbol;
    }

    public String getOpName() {
        return opName;
    }

    public String getOpSymbol() {
        return opSymbol;
    }
}
