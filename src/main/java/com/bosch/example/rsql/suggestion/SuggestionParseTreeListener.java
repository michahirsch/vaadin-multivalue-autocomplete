package com.bosch.example.rsql.suggestion;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

public class SuggestionParseTreeListener implements ParseTreeListener {

    private final Map<Integer, Token> startStopMap = new HashMap<>();

    @Override
    public void visitTerminal(final TerminalNode node) {
        final Token symbol = node.getSymbol();
        startStopMap.put(symbol.getType(), symbol);
    }

    @Override
    public void visitErrorNode(final ErrorNode node) {
    }

    @Override
    public void enterEveryRule(final ParserRuleContext ctx) {
    }

    @Override
    public void exitEveryRule(final ParserRuleContext ctx) {
    }

    public Optional<Token> getTokenAtCursorPosition(final int cursorPos) {
        return startStopMap.entrySet().stream()
                .filter(keyvalue -> keyvalue.getValue().getStartIndex() <= cursorPos
                        && keyvalue.getValue().getStopIndex() >= cursorPos)
                .map(keyvalue -> keyvalue.getValue()).findFirst();
    }
}
