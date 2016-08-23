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
 *
 */
public interface AutoCompleteTextFieldServerRpc extends ServerRpc {

    void getSuggestions(final String queryText, final int cursor);

    void suggestionSelected(String selectedItem, int tokenStart, int tokenEnd, int cursorPos);

}