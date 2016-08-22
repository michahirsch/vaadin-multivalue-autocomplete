/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.bosch.example.ui.component;

import java.util.List;

import com.bosch.example.rsql.suggestion.RsqlSuggestionHelper.SuggestionContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class SuggestionWindow extends Window {

    private final VerticalLayout rootLayout = new VerticalLayout();

    public SuggestionWindow(final SuggestionContext context, final SuggestionCallback suggestionCallback) {
        setModal(false);
        setDraggable(false);
        setImmediate(true);
        setClosable(false);
        setResizable(false);
        setContent(rootLayout);
        buildLayout(context, suggestionCallback);
    }

    private void buildLayout(final SuggestionContext context, final SuggestionCallback suggestionCallback) {

        final List<String> suggestions;
        if (context.isSyntaxError()) {
            suggestions = context.getSyntaxErrorContext().getSuggestions();
        } else {
            suggestions = context.getCursorPositionContext().getSuggestions();
        }

        if (suggestions != null) {
            suggestions.forEach(suggestion -> {
                final Button label = new Button(suggestion);
                rootLayout.addComponent(label);
                label.addClickListener((event) -> {
                    close();
                    suggestionCallback.selected(event.getButton().getCaption());
                });
            });
        }

    }

    @FunctionalInterface
    public interface SuggestionCallback {
        void selected(final String suggestion);
    }
}