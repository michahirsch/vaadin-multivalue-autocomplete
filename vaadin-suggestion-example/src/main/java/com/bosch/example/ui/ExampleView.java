package com.bosch.example.ui;

import com.bosch.example.rsql.SuggestContext;
import com.bosch.example.rsql.SuggestOracle;
import com.bosch.example.rsql.TokenSuggestionContext;
import com.bosch.example.ui.component.AutocompleteTextfield;
import com.bosch.example.ui.component.TextFieldSuggestionBox;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = "")
public class ExampleView extends VerticalLayout implements View {

    private static final long serialVersionUID = 1L;

    @Override
    public void enter(final ViewChangeEvent event) {

        this.setSizeFull();

        final AutocompleteTextfield textField = new AutocompleteTextfield();
        new TextFieldSuggestionBox(textField, getSuggestOracle());
        textField.setWidth("800px");
        addComponent(textField);
    }

    private SuggestOracle getSuggestOracle() {
        return new DummySuggestOracle();
    }

    private static final class DummySuggestOracle implements SuggestOracle {

        @Override
        public SuggestContext getSuggestion(final String query, final int cursorPos) {
            return new SuggestContext() {

                @Override
                public boolean hasSyntaxError() {
                    return false;
                }

                @Override
                public TokenSuggestionContext getSyntaxErrorContext() {
                    return null;
                }

                @Override
                public TokenSuggestionContext getCursorContext() {
                    return null;
                }
            };
        }

    }
}