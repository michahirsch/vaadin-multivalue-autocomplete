/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.bosch.example.ui.component.client;

import java.io.Serializable;
import java.util.List;

/**
 * @author Michael Hirsch
 *
 */
public class ClientSuggestionTokenContext implements Serializable {

    private static final long serialVersionUID = 1L;
    private int tokenStart;
    private int tokenEnd;
    private List<String> suggestions;

    public ClientSuggestionTokenContext() {

    }

    public ClientSuggestionTokenContext(final int tokenStart, final int tokenEnd, final List<String> suggestions) {
        this.tokenStart = tokenStart;
        this.tokenEnd = tokenEnd;
        this.suggestions = suggestions;
    }

    public int getTokenStart() {
        return tokenStart;
    }

    public int getTokenEnd() {
        return tokenEnd;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }

    public void setTokenStart(final int tokenStart) {
        this.tokenStart = tokenStart;
    }

    public void setTokenEnd(final int tokenEnd) {
        this.tokenEnd = tokenEnd;
    }

    public void setSuggestions(final List<String> suggestions) {
        this.suggestions = suggestions;
    }
}
