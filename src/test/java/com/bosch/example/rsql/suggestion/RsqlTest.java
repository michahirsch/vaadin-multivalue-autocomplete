package com.bosch.example.rsql.suggestion;

import org.junit.Test;

import com.bosch.example.rsql.suggestion.RsqlSuggestionHelper.SuggestionContext;

public class RsqlTest {

    @Test
    public void rsqlTest() {

        final String rsqlTest = "name ";
        final int cursorPos = 11;

        final SuggestionContext parse = RsqlSuggestionHelper.parse(rsqlTest, cursorPos);

        System.out.println(parse);

    }

}
