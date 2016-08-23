package com.bosch.example.rsql.suggestion;

import java.util.Optional;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.google.gwt.thirdparty.guava.common.collect.ArrayListMultimap;

public class SuggestionParseTreeListener implements ParseTreeListener {

    private final ArrayListMultimap<Integer, Token> startStopMap = ArrayListMultimap.create();
    private int longestToken = 0;

    @Override
    public void visitTerminal(final TerminalNode node) {
        final Token symbol = node.getSymbol();
        startStopMap.put(symbol.getType(), symbol);
        longestToken = Math.max(longestToken, symbol.getStopIndex());
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
        final int maxCursorPos = Math.min(cursorPos, longestToken);
        return startStopMap.entries().stream()
                .filter(keyvalue -> keyvalue.getValue().getStartIndex() <= cursorPos
                        && keyvalue.getValue().getStopIndex() >= maxCursorPos)
                .map(keyvalue -> keyvalue.getValue()).findFirst();
    }
}
