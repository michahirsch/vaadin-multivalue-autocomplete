/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.bosch.example.ui.component.client;

import java.util.List;

import com.bosch.example.ui.component.TextFieldSuggestionBox;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.PopupPanel;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.VConsole;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.VTextField;
import com.vaadin.shared.ui.Connect;

/**
 * Connector for the AutoCompleteTextField.
 *
 */
@Connect(TextFieldSuggestionBox.class)
public class AutoCompleteTextFieldConnector extends AbstractExtensionConnector {

    private static final long serialVersionUID = 1L;

    AutoCompleteTextFieldServerRpc rpc = getRpcProxy(AutoCompleteTextFieldServerRpc.class);
    final PopupPanel panel = new PopupPanel(true, false);

    private VTextField textFieldWidget;
    private CustomMenubar customMenubar;

    @Override
    protected void init() {
        super.init();

        registerRpc(AutocompleteTextFieldClientRpc.class, new AutocompleteTextFieldClientRpc() {
            @Override
            public void showSuggestions(final ClientSuggestionTokenContext errorContext,
                    final ClientSuggestionTokenContext cursorContext) {
                customMenubar.clearItems();
                if (errorContext != null) {
                    final List<String> errorSuggestions = errorContext.getSuggestions();
                    if (errorSuggestions != null) {
                        customMenubar.setSuggestions(errorSuggestions, errorContext.getTokenStart(),
                                errorContext.getTokenEnd());
                    }
                } else if (cursorContext != null) {
                    final List<String> cursorSuggestions = cursorContext.getSuggestions();
                    if (cursorSuggestions != null && !cursorSuggestions.isEmpty()) {
                        customMenubar.setSuggestions(cursorSuggestions, cursorContext.getTokenStart(),
                                cursorContext.getTokenEnd());
                    }
                }

                if (customMenubar.getItems().size() > 0) {
                    panel.showRelativeTo(textFieldWidget);
                    return;
                }
                panel.hide();
            }
        });
    }

    @Override
    protected void extend(final ServerConnector target) {
        textFieldWidget = (VTextField) ((ComponentConnector) target).getWidget();
        customMenubar = new CustomMenubar(true, rpc, textFieldWidget, panel);
        panel.setWidget(customMenubar);
        panel.setStyleName("suggestion-popup");
        // panel.add(select);

        textFieldWidget.addDomHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(final KeyDownEvent event) {
                customMenubar.focus();
                if (panel.isAttached()) {
                    VConsole.error("in key down event inside the condition " + event);
                    popupKeyDown(event);
                } else {
                    inputFieldKeyDown(event);
                }
            }
        }, KeyDownEvent.getType());
    }

    /**
     * Triggered when a key was pressed in the suggestion popup.
     * 
     * @param event
     *            The KeyDownEvent of the key
     */
    private void popupKeyDown(final KeyDownEvent event) {
        // Propagation of handled events is stopped so other handlers such as
        // shortcut key handlers do not also handle the same events.
        VConsole.error("in popupKeyDown event " + event);
        switch (event.getNativeKeyCode()) {
        case KeyCodes.KEY_DOWN:
            VConsole.error("in case KeyCodes.KEY_DOWN " + event);
            selectNextItem();
            DOM.eventPreventDefault(DOM.eventGetCurrentEvent());
            event.stopPropagation();
            break;
        case KeyCodes.KEY_UP:
            VConsole.error("in case KeyCodes.KEY_UP" + event);
            selectPrevItem();
            DOM.eventPreventDefault(DOM.eventGetCurrentEvent());
            event.stopPropagation();
            break;
        case KeyCodes.KEY_ESCAPE:
            VConsole.error("in  case KeyCodes.KEY_ESCAPE:" + event);
            reset();
            panel.hide();
            DOM.eventPreventDefault(DOM.eventGetCurrentEvent());
            event.stopPropagation();
            break;
        case KeyCodes.KEY_TAB:
        case KeyCodes.KEY_ENTER:
            VConsole.error("in  case KeyCodes.KEY_TAB: case KeyCodes.KEY_ENTER:" + event);
            final int selected = customMenubar.getSelectedIndex();
            if (selected != -1) {
                customMenubar.doItemAction(customMenubar.getItems().get(selected), true);
            } else {
                panel.hide();
            }
            event.stopPropagation();
            break;
        }

    }

    /**
     * Triggered when a key is pressed in the text box
     * 
     * @param event
     *            The KeyDownEvent
     */
    private void inputFieldKeyDown(final KeyDownEvent event) {
        VConsole.error("in inputFieldKeyDown" + event.getNativeKeyCode());
        panel.hide();
        final int cursorPos = textFieldWidget.getCursorPos();
        rpc.getSuggestions(textFieldWidget.getValue(), cursorPos);
    }

    private void reset() {
        VConsole.error("in reset");
        panel.hide();
    }

    /**
     * Selects the next item in the filtered selections
     */
    private void selectNextItem() {
        VConsole.error("in selectNextItem");
        final int index = customMenubar.getSelectedIndex() + 1;
        VConsole.error("in selectNextItem the index = " + index);
        if (customMenubar.getItems().size() > index) {
            customMenubar.selectItem(customMenubar.getItems().get(index));

        }
    }

    /**
     * Selects the previous item in the filtered selections
     */
    public void selectPrevItem() {
        VConsole.error("in selectPrevItem");
        final int index = customMenubar.getSelectedIndex() - 1;
        if (index > -1) {
            customMenubar.selectItem(customMenubar.getItems().get(index));

        } else {
            if (!customMenubar.getItems().isEmpty()) {
                customMenubar.selectItem(customMenubar.getLastItem());
            }
        }
    }

}