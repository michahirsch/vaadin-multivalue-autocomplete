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
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Window;

public class SuggestionWindow extends Window {

    private final ListSelect select = new ListSelect("Suggestions");
    private final SuggestionCallback suggestionCallback;

    public SuggestionWindow(final SuggestionCallback suggestionCallback) {
        setModal(false);
        setDraggable(false);
        setImmediate(true);
        setClosable(false);
        setResizable(false);
        setContent(select);
        select.setNullSelectionAllowed(false);
        select.setRows(4);
        select.setMultiSelect(false);
        this.suggestionCallback = suggestionCallback;
    }

    public void update(final SuggestionContext context) {
        select.focus();
        final List<String> suggestions;
        if (context.isSyntaxError()) {
            suggestions = context.getSyntaxErrorContext().getSuggestions();
        } else {
            suggestions = context.getCursorPositionContext().getSuggestions();
        }

        if (suggestions != null) {
            suggestions.forEach(suggestion -> {
                select.addItem(suggestion);
            });
        }
        select.addShortcutListener(new EnterShortCutListener());
    }

    @FunctionalInterface
    public interface SuggestionCallback {
        void selected(final String suggestion);
    }

    private final class EnterShortCutListener extends ShortcutListener {

        private static final long serialVersionUID = 1L;

        public EnterShortCutListener() {
            super("Enter", KeyCode.ENTER, new int[] {});
        }

        @Override
        public void handleAction(final Object sender, final Object target) {
            final Object value = select.getValue();
            suggestionCallback.selected(String.valueOf(value));
        }

    }
}