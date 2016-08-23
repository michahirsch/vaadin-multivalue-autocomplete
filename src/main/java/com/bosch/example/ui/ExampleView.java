package com.bosch.example.ui;

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
        new TextFieldSuggestionBox(textField);
        textField.setWidth("800px");
        addComponent(textField);
    }
}