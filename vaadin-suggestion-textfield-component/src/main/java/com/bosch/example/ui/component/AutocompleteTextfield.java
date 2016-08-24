package com.bosch.example.ui.component;

import com.vaadin.ui.TextField;

public class AutocompleteTextfield extends TextField {

    private static final long serialVersionUID = 1L;

    public AutocompleteTextfield() {
        setImmediate(true);
        setTextChangeEventMode(TextChangeEventMode.EAGER);
    }

}
