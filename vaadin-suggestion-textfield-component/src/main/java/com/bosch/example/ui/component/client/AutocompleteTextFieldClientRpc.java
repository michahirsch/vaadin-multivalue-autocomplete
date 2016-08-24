/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.bosch.example.ui.component.client;

import com.vaadin.shared.communication.ClientRpc;

/**
 * Client RPC for the AutocompleteTextField.
 *
 */
public interface AutocompleteTextFieldClientRpc extends ClientRpc {

    /**
     * Shows the suggestions.
     * 
     * @param syntaxErrorSuggestion
     * @param cursorSuggestions
     */
    void showSuggestions(final ClientSuggestionTokenContext syntaxErrorSuggestion,
            final ClientSuggestionTokenContext cursorSuggestions);

}
