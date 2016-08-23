package com.bosch.example.ui.component;

import com.bosch.example.rsql.suggestion.RsqlSuggestionHelper;
import com.bosch.example.rsql.suggestion.RsqlSuggestionHelper.CursorPositionSuggestionContext;
import com.bosch.example.rsql.suggestion.RsqlSuggestionHelper.SuggestionContext;
import com.bosch.example.rsql.suggestion.RsqlSuggestionHelper.SyntaxErrorContext;
import com.bosch.example.ui.component.client.AutoCompleteTextFieldServerRpc;
import com.bosch.example.ui.component.client.AutocompleteTextFieldClientRpc;
import com.bosch.example.ui.component.client.ClientSuggestionTokenContext;
import com.vaadin.server.AbstractExtension;

public class TextFieldSuggestionBox extends AbstractExtension implements AutoCompleteTextFieldServerRpc {

    private static final long serialVersionUID = 1L;
    private final AutocompleteTextfield textField;

    public TextFieldSuggestionBox(final AutocompleteTextfield textField) {
        this.textField = textField;
        extend(textField);
        registerRpc(this, AutoCompleteTextFieldServerRpc.class);
    }

    @Override
    public void getSuggestions(final String queryText, final int cursor) {
        final SuggestionContext parse = RsqlSuggestionHelper.parse(queryText, cursor);
        final SyntaxErrorContext syntaxErrorContext = parse.getSyntaxErrorContext();
        final CursorPositionSuggestionContext cursorPositionContext = parse.getCursorPositionContext();
        ClientSuggestionTokenContext syntaxErrorSuggestions = null;
        ClientSuggestionTokenContext cursorSuggestions = null;
        if (syntaxErrorContext != null) {
            syntaxErrorSuggestions = new ClientSuggestionTokenContext(syntaxErrorContext.getTokenStart(),
                    syntaxErrorContext.getTokenEnd(), syntaxErrorContext.getSuggestions());
        }

        if (cursorPositionContext != null) {
            cursorSuggestions = new ClientSuggestionTokenContext(cursorPositionContext.getTokenStart(),
                    cursorPositionContext.getTokenEnd(), cursorPositionContext.getSuggestions());
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

}
