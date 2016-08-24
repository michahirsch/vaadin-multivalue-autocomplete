package com.bosch.example.ui.component.client;

import java.util.List;

import com.bosch.example.ui.component.TextFieldSuggestionBox;
import com.bosch.example.ui.component.client.MyListSelect.TokenStartEnd;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.PopupPanel;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.VTextField;
import com.vaadin.shared.ui.Connect;

@Connect(TextFieldSuggestionBox.class)
public class AutoCompleteTextFieldConnector extends AbstractExtensionConnector {

    private static final long serialVersionUID = 1L;

    AutoCompleteTextFieldServerRpc rpc = getRpcProxy(AutoCompleteTextFieldServerRpc.class);
    final MyListSelect select = new MyListSelect();
    final PopupPanel panel = new PopupPanel(true, false);

    private VTextField textFieldWidget;

    @Override
    protected void init() {
        super.init();

        registerRpc(AutocompleteTextFieldClientRpc.class, new AutocompleteTextFieldClientRpc() {
            @Override
            public void showSuggestions(final ClientSuggestionTokenContext errorContext,
                    final ClientSuggestionTokenContext cursorContext) {
                select.clear();
                if (errorContext != null) {
                    final List<String> errorSuggestions = errorContext.getSuggestions();
                    if (errorSuggestions != null) {
                        for (final String suggestion : errorSuggestions) {
                            select.addItem(errorContext.getTokenStart(), errorContext.getTokenEnd(), suggestion);
                        }
                    }
                } else if (cursorContext != null) {
                    final List<String> cursorSuggestions = cursorContext.getSuggestions();
                    if (cursorSuggestions != null && !cursorSuggestions.isEmpty()) {
                        for (final String suggestion : cursorSuggestions) {
                            select.addItem(cursorContext.getTokenStart(), cursorContext.getTokenEnd(), suggestion);
                        }
                    }
                }

                if (select.getOptionsContainer().getItemCount() > 0) {
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

        select.setEnabled(true);

        panel.add(select);

        select.addDomHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(final KeyDownEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    final TokenStartEnd tokenStartEnd = select.getTokenStartEnd(select.getSelectedItem());
                    rpc.suggestionSelected(select.getSelectedItem(), tokenStartEnd.getStart(), tokenStartEnd.getEnd(),
                            textFieldWidget.getCursorPos());
                    panel.hide();
                    textFieldWidget.setFocus(true);

                }
            }
        }, KeyDownEvent.getType());

        textFieldWidget.addDomHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(final KeyDownEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_DOWN) {
                    if (select.isVisible()) {
                        select.focus();
                        if (select.getOptionsContainer().getItemCount() > 0 && select.getSelectedItem() == null) {
                            select.getOptionsContainer().setItemSelected(0, true);
                        }
                    }
                } else if (event.getNativeKeyCode() == KeyCodes.KEY_ESCAPE) {
                    panel.hide();
                }
            }
        }, KeyDownEvent.getType());

        textFieldWidget.addDomHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(final KeyUpEvent event) {
                panel.hide();
                final int cursorPos = textFieldWidget.getCursorPos();
                rpc.getSuggestions(textFieldWidget.getValue(), cursorPos);
            }
        }, KeyUpEvent.getType());
    }

}