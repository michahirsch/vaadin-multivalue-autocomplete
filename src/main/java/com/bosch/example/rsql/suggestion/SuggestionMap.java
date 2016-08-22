/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.bosch.example.rsql.suggestion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SuggestionMap {

    private static Map<String, List<String>> SUGGESTIONS = new HashMap<String, List<String>>();

    static {
        final List<String> selectors = Arrays.stream(TargetFieldSuggestions.values()).map(sel -> sel.getFieldName())
                .collect(Collectors.toList());
        final List<String> compOperators = Arrays.stream(OperatorSuggestions.values()).map(sel -> sel.getOpSymbol())
                .collect(Collectors.toList());
        compOperators.add("==");
        compOperators.add("!=");
        compOperators.add("=le=");
        compOperators.add("=ge=");

        SUGGESTIONS.put("COMPARISONOP", compOperators);
        SUGGESTIONS.put("SELECTOR", selectors);
    }

    public static List<String> getSuggestions(final String field) {
        return SUGGESTIONS.get(field);
    }

}
