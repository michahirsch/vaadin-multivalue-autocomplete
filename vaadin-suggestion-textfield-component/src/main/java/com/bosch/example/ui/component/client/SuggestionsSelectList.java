/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.bosch.example.ui.component.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.user.client.ui.ListBox;
import com.vaadin.client.UIDL;
import com.vaadin.client.ui.VOptionGroupBase;

/**
 * Holds the suggestions list.
 *
 */
public class SuggestionsSelectList extends VOptionGroupBase {

    public static final String CLASSNAME = "autocomplete";

    protected ListBox select;
    private final Map<String, TokenStartEnd> tokenMap = new HashMap<>();

    /**
     * Constructor.
     */
    public SuggestionsSelectList() {
        super(new ListBox(), CLASSNAME);
        select = getOptionsContainer();
        select.setVisibleItemCount(10);
        select.setMultipleSelect(false);
        setStyleName(CLASSNAME);
    }

    @Override
    public void setStyleName(final String style) {
        super.setStyleName(style);
        updateStyleNames();
    }

    @Override
    public void setStylePrimaryName(final String style) {
        super.setStylePrimaryName(style);
        updateStyleNames();
    }

    protected void updateStyleNames() {
        container.setStyleName(getStylePrimaryName());
        select.setStyleName(getStylePrimaryName() + "-select");
    }

    public TokenStartEnd getTokenStartEnd(final String suggestion) {
        return tokenMap.get(suggestion);
    }

    public void addItem(final int tokenStart, final int tokenEnd, final String suggestion) {
        tokenMap.put(suggestion, new TokenStartEnd(tokenStart, tokenEnd));
        getOptionsContainer().addItem(suggestion);
    }

    public void clear() {
        getOptionsContainer().clear();
    }

    protected ListBox getOptionsContainer() {
        return (ListBox) optionsContainer;
    }

    @Override
    public String getSelectedItem() {
        return super.getSelectedItem();
    }

    @Override
    public void onChange(final ChangeEvent event) {
    }

    @Override
    public void focus() {
        select.setFocus(true);
    }

    @Override
    public void setTabIndex(final int tabIndex) {
        getOptionsContainer().setTabIndex(tabIndex);
    }

    @Override
    public void buildOptions(final UIDL uidl) {

    }

    @Override
    protected String[] getSelectedItems() {
        final ArrayList<String> selectedItemKeys = new ArrayList<String>();
        for (int i = 0; i < select.getItemCount(); i++) {
            if (select.isItemSelected(i)) {
                selectedItemKeys.add(select.getValue(i));
            }
        }
        return selectedItemKeys.toArray(new String[selectedItemKeys.size()]);
    }

    @Override
    protected void updateEnabledState() {
        select.setEnabled(isEnabled() && !isReadonly());
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
