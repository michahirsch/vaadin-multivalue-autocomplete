/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.bosch.example.ui.component.client;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gwt.aria.client.Roles;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.impl.FocusImpl;
import com.vaadin.client.VConsole;
import com.vaadin.client.WidgetUtil;
import com.vaadin.client.ui.VTextField;
import com.vaadin.client.ui.menubar.MenuBar;
import com.vaadin.client.ui.menubar.MenuItem;

public class CustomMenubar extends MenuBar {

    private static final String DEPENDENT_STYLENAME_SELECTED_ITEM = "selected";
    private final Map<String, TokenStartEnd> tokenMap = new HashMap<>();
    private final AutoCompleteTextFieldServerRpc rpc;
    private final VTextField textFieldWidget;
    private final PopupPanel panel;

    /**
     * Constructor.
     * 
     * @param verticalMenu
     * @param rpc
     * @param textFieldWidget
     * @param panel
     */
    public CustomMenubar(final boolean verticalMenu, final AutoCompleteTextFieldServerRpc rpc,
            final VTextField textFieldWidget, final PopupPanel panel) {
        super(verticalMenu);
        this.rpc = rpc;
        this.textFieldWidget = textFieldWidget;
        this.panel = panel;
    }

    @Override
    protected void doItemAction(final MenuItem item, final boolean fireCommand) {

        if (fireCommand) {
            // Fire the item's command.
            final Command cmd = item.getCommand();
            if (cmd != null) {
                Scheduler.get().scheduleDeferred(cmd);
            }
            return;
        }

        // Ensure that the item is selected.
        selectItem(item);

    }

    public TokenStartEnd getTokenStartEnd(final String suggestion) {
        return tokenMap.get(suggestion);
    }

    public void focus() {
        FocusImpl.getFocusImplForWidget().focus(getElement());
    }

    /**
     * Sets the suggestions rendered in the menu
     * 
     * @param suggestions
     *            The suggestions to be rendered in the menu
     * @param textFieldWidget
     * @param panel
     */
    public void setSuggestions(final Collection<String> suggestions, final int tokenStart, final int tokenEnd) {

        clearItems();
        final Iterator<String> it = suggestions.iterator();
        boolean isFirstIteration = true;
        while (it.hasNext()) {
            final String s = it.next();
            final MenuItem mi = new MenuItem(s, true, new Command() {

                @Override
                public void execute() {
                    VConsole.error("BEFORE DOING SOMETHING selected item - " + getSelectedItem());
                    final TokenStartEnd tokenStartEnd = getTokenStartEnd(getSelectedItem().getText());
                    VConsole.error("Token start end " + tokenStartEnd);
                    VConsole.error("Server rpc " + rpc);
                    VConsole.error("Popup Panel " + panel);
                    rpc.suggestionSelected(getSelectedItem().getText(), tokenStartEnd.getStart(),
                            tokenStartEnd.getEnd(), textFieldWidget.getCursorPos());
                    VConsole.error("AFTER RPC");
                    panel.hide();
                    // textFieldWidget.setFocus(true);
                }
            });
            addStyleDependentName(DEPENDENT_STYLENAME_SELECTED_ITEM);
            tokenMap.put(s, new TokenStartEnd(tokenStart, tokenEnd));
            Roles.getListitemRole().set(mi.getElement());

            WidgetUtil.sinkOnloadForImages(mi.getElement());

            this.addItem(mi);

            // By default, first item on the list is always highlighted,
            // unless adding new items is allowed.
            if (isFirstIteration) {
                selectItem(mi);
            }
            isFirstIteration = false;
        }
    }

    /**
     * Suggestion Token start and end index.
     *
     */
    public class TokenStartEnd {
        final int start;
        final int end;

        /**
         * Constructor.
         * 
         * @param start
         * @param end
         */
        public TokenStartEnd(final int start, final int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }

}
