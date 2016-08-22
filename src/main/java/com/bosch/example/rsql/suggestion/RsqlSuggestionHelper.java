/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.bosch.example.rsql.suggestion;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.IntervalSet;

import com.google.gwt.thirdparty.guava.common.base.Throwables;

import rsql.RsqlLexer;
import rsql.RsqlParser;

/**
 *
 */
public class RsqlSuggestionHelper {

    public static SuggestionContext parse(final String rsql, final int cursorPosition) {
        CharStream inputCharStream;
        try {
            inputCharStream = new ANTLRInputStream(new StringReader(rsql));
        } catch (final IOException e) {
            throw Throwables.propagate(e);
        }
        final TokenSource tokenSource = new RsqlLexer(inputCharStream);
        final TokenStream inputTokenStream = new CommonTokenStream(tokenSource);
        final RsqlParser parser = new RsqlParser(inputTokenStream);
        final SuggestionErrorListener errorListener = new SuggestionErrorListener();
        parser.addErrorListener(errorListener);
        final SuggestionParseTreeListener parserListener = new SuggestionParseTreeListener();
        parser.addParseListener(parserListener);

        parser.expr();

        final SuggestionContext suggestionContext = new SuggestionContext();

        suggestionContext.syntaxError = errorListener.isErrorOccurred();
        if (errorListener.isErrorOccurred()) {
            final SyntaxErrorContext errorContext = new SyntaxErrorContext();
            final Token errorToken = errorListener.getErrorToken().getToken();
            errorContext.tokenStart = errorToken.getStartIndex();
            errorContext.tokenEnd = errorToken.getStopIndex();

            final IntervalSet expectedTokenType = errorListener.getErrorToken().getExpectedTokenType();
            expectedTokenType.getIntervals().forEach(interval -> {
                final int start = interval.a;
                final int end = interval.b;
                for (int index = start; index <= end; index++) {
                    final String symbolicName = parser.getVocabulary().getSymbolicName(index);
                    errorContext.suggestions.addAll(SuggestionMap.getSuggestions(symbolicName));
                }
            });
            suggestionContext.syntaxErrorContext = errorContext;
        }

        final Optional<Token> tokenAtCursorPosition = parserListener.getTokenAtCursorPosition(cursorPosition);
        if (tokenAtCursorPosition.isPresent()) {
            final CursorPositionSuggestionContext cursorPositionSuggestionContext = new CursorPositionSuggestionContext();
            cursorPositionSuggestionContext.currentCursor = cursorPosition;
            cursorPositionSuggestionContext.tokenStart = tokenAtCursorPosition.get().getStartIndex();
            cursorPositionSuggestionContext.tokenEnd = tokenAtCursorPosition.get().getStopIndex();
            cursorPositionSuggestionContext.suggestions = SuggestionMap
                    .getSuggestions(parser.getVocabulary().getSymbolicName(tokenAtCursorPosition.get().getType()));
            suggestionContext.cursorPositionContext = cursorPositionSuggestionContext;
        }

        return suggestionContext;
    }

    public static class SuggestionContext {

        private boolean syntaxError;
        private SyntaxErrorContext syntaxErrorContext;
        private CursorPositionSuggestionContext cursorPositionContext;

        public SyntaxErrorContext getSyntaxErrorContext() {
            return syntaxErrorContext;
        }

        public CursorPositionSuggestionContext getCursorPositionContext() {
            return cursorPositionContext;
        }

        public boolean isSyntaxError() {
            return syntaxError;
        }

        public boolean hasSuggestions() {
            return (syntaxErrorContext != null && syntaxErrorContext.suggestions != null
                    && !syntaxErrorContext.suggestions.isEmpty())
                    || (cursorPositionContext != null && cursorPositionContext.suggestions != null
                            && !cursorPositionContext.suggestions.isEmpty());
        }

        @Override
        public String toString() {
            return "SuggestionContext [syntaxError=" + syntaxError + ", syntaxErrorContext=" + syntaxErrorContext
                    + ", cursorPositionContext=" + cursorPositionContext + "]";
        }

    }

    public static class SyntaxErrorContext {
        private int tokenStart;
        private int tokenEnd;
        private final List<String> suggestions = new ArrayList<>();

        public List<String> getSuggestions() {
            return suggestions;
        }

        public int getTokenStart() {
            return tokenStart;
        }

        public int getTokenEnd() {
            return tokenEnd;
        }

        @Override
        public String toString() {
            return "SyntaxErrorContext [tokenStart=" + tokenStart + ", tokenEnd=" + tokenEnd + ", suggestions="
                    + suggestions + "]";
        }
    }

    public static class CursorPositionSuggestionContext {
        private int tokenStart;
        private int tokenEnd;
        private int currentCursor;
        private List<String> suggestions = new ArrayList<>();

        public int getTokenStart() {
            return tokenStart;
        }

        public int getTokenEnd() {
            return tokenEnd;
        }

        public int getCurrentCursor() {
            return currentCursor;
        }

        public List<String> getSuggestions() {
            return suggestions;
        }

        @Override
        public String toString() {
            return "CursorPositionSuggestionContext [tokenStart=" + tokenStart + ", tokenEnd=" + tokenEnd
                    + ", currentCursor=" + currentCursor + ", suggestions=" + suggestions + "]";
        }

    }
}
