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
            public void showSuggestions(final ClientSuggestionTokenContext context) {
                select.clear();
                final List<String> suggestions = context.getSuggestions();
                if (suggestions != null && !suggestions.isEmpty()) {
                    for (final String suggestion : suggestions) {
                        select.addItem(context.getTokenStart(), context.getTokenEnd(), suggestion);
                    }
                    panel.showRelativeTo(textFieldWidget);
                } else {
                    panel.hide();
                }
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
                    select.focus();
                }
            }
        }, KeyDownEvent.getType());

        textFieldWidget.addDomHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(final KeyUpEvent event) {
                panel.hide();
                rpc.getSuggestions(textFieldWidget.getValue(), textFieldWidget.getCursorPos());
            }
        }, KeyUpEvent.getType());
    }

}