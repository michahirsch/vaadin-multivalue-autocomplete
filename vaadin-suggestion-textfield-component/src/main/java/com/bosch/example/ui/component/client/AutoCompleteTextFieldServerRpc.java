/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.bosch.example.ui.component.client;

import com.vaadin.shared.communication.ServerRpc;

/**
 * Server RPC for the AutoCompleteTextField.
 */
public interface AutoCompleteTextFieldServerRpc extends ServerRpc {

    /**
     * Gets the list of suggestions based on the query text input in the
     * AutoCompleteTextField.
     * 
     * @param queryText
     * @param cursor
     */
    void getSuggestions(final String queryText, final int cursor);

    /**
     * Selects the suggestion from the available/filtered suggestions in the
     * popup.
     * 
     * @param selectedItem
     * @param tokenStart
     * @param tokenEnd
     * @param cursorPos
     */
    void suggestionSelected(String selectedItem, int tokenStart, int tokenEnd, int cursorPos);

}