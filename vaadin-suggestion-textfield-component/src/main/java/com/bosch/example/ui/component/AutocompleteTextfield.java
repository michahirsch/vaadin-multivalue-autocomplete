/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.bosch.example.ui.component;

import com.vaadin.ui.TextField;

/**
 * Auto suggest textfield for muti suggestions.
 *
 */
public class AutocompleteTextfield extends TextField {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     */
    public AutocompleteTextfield() {
        setImmediate(true);
        setTextChangeEventMode(TextChangeEventMode.EAGER);
    }

}
