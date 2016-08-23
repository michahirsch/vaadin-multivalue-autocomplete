package com.bosch.example.ui.component.client;

import com.vaadin.shared.communication.ClientRpc;

public interface AutocompleteTextFieldClientRpc extends ClientRpc {

    void showSuggestions(final ClientSuggestionTokenContext syntaxErrorSuggestion);

}
