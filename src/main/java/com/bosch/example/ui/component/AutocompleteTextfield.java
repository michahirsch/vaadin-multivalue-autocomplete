package com.bosch.example.ui.component;

import com.bosch.example.rsql.suggestion.RsqlSuggestionHelper;
import com.bosch.example.rsql.suggestion.RsqlSuggestionHelper.SuggestionContext;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.UserError;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

public class AutocompleteTextfield extends TextField {

    private static final long serialVersionUID = 1L;

    public AutocompleteTextfield() {
        setImmediate(true);
        addShortcutListener(new CtrlSpaceShortCutListener());
        setTextChangeEventMode(TextChangeEventMode.EAGER);
        addTextChangeListener((event) -> textChanged(event));
    }

    private void textChanged(final TextChangeEvent event) {
        printOutSuggestions(event.getText(), false);
    }

    private void printOutSuggestions(final String rsqlText, final boolean showWindow) {
        final SuggestionContext parse = RsqlSuggestionHelper.parse(rsqlText, getCursorPosition());
        if (parse.isSyntaxError()) {
            setComponentError(new UserError("Syntax Error"));
        } else {
            setComponentError(null);
        }
        if (parse.hasSuggestions() && showWindow) {
            UI.getCurrent().addWindow(new SuggestionWindow(parse, new SuggestionWindow.SuggestionCallback() {
                @Override
                public void selected(final String suggestion) {
                    int start;
                    int end;
                    if (parse.isSyntaxError()) {
                        start = parse.getSyntaxErrorContext().getTokenStart();
                        end = parse.getSyntaxErrorContext().getTokenEnd();
                    } else {
                        start = parse.getCursorPositionContext().getTokenStart();
                        end = parse.getCursorPositionContext().getTokenEnd();
                    }

                    final StringBuilder builder = new StringBuilder(getValue());
                    final int min = Math.min(start, end);
                    builder.replace(min, min + suggestion.length(), suggestion);
                    setValue(builder.toString());
                }
            }));
        }
    }

    private final class CtrlSpaceShortCutListener extends ShortcutListener {

        private static final long serialVersionUID = 1L;

        public CtrlSpaceShortCutListener() {
            super("Ctrl + Space", KeyCode.SPACEBAR, new int[] { ModifierKey.CTRL });
        }

        @Override
        public void handleAction(final Object sender, final Object target) {
            printOutSuggestions(AutocompleteTextfield.this.getValue(), true);
        }
    }
}
