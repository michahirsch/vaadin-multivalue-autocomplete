/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.bosch.example.ui.component;

import com.bosch.example.rsql.SuggestContext;
import com.bosch.example.rsql.SuggestOracle;
import com.bosch.example.rsql.TokenSuggestionContext;
import com.bosch.example.ui.component.client.AutoCompleteTextFieldServerRpc;
import com.bosch.example.ui.component.client.AutocompleteTextFieldClientRpc;
import com.bosch.example.ui.component.client.ClientSuggestionTokenContext;
import com.vaadin.server.AbstractExtension;

/**
 * Extension for the AutoCompleteTexfield.
 *
 */
public class TextFieldSuggestionBox extends AbstractExtension implements AutoCompleteTextFieldServerRpc {

    private static final long serialVersionUID = 1L;
    private final AutocompleteTextfield textField;
    private final SuggestOracle suggestOracle;

    /**
     * Constructor.
     * 
     * @param textField
     * @param suggestOracle
     */
    public TextFieldSuggestionBox(final AutocompleteTextfield textField, final SuggestOracle suggestOracle) {
        this.textField = textField;
        this.suggestOracle = suggestOracle;
        extend(textField);
        registerRpc(this, AutoCompleteTextFieldServerRpc.class);
    }

    @Override
    public void getSuggestions(final String queryText, final int cursor) {
        final SuggestContext context = suggestOracle.getSuggestion(queryText, cursor);

        ClientSuggestionTokenContext syntaxErrorSuggestions = null;
        ClientSuggestionTokenContext cursorSuggestions = null;

        if (context.getSyntaxErrorContext() != null) {
            final TokenSuggestionContext syntaxErrorContext = context.getSyntaxErrorContext();
            syntaxErrorSuggestions = new ClientSuggestionTokenContext(syntaxErrorContext.getStart(),
                    syntaxErrorContext.getEnd(), syntaxErrorContext.getSuggestions());
        }
        if (context.getCursorContext() != null) {
            final TokenSuggestionContext cursorErrorContext = context.getCursorContext();
            cursorSuggestions = new ClientSuggestionTokenContext(cursorErrorContext.getStart(),
                    cursorErrorContext.getEnd(), cursorErrorContext.getSuggestions());
        }
        getRpcProxy(AutocompleteTextFieldClientRpc.class).showSuggestions(syntaxErrorSuggestions, cursorSuggestions);
    }

    @Override
    public void suggestionSelected(final String suggestion, final int tokenStart, final int tokenEnd,
            final int cursorPos) {
        final String tmpSuggestion = suggestion;
        final String text = textField.getValue();
        final StringBuilder builder = new StringBuilder(text);
        builder.replace(tokenStart, tokenEnd + 1, tmpSuggestion);
        textField.setValue(builder.toString());
    }

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
