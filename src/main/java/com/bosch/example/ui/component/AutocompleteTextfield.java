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

    private SuggestionWindow suggestionWindow;

    public AutocompleteTextfield() {
        setImmediate(true);
        addShortcutListener(new CtrlSpaceShortCutListener());
        addShortcutListener(new SpaceCloseWindowListener());
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
            if (suggestionWindow != null) {
                suggestionWindow.close();
                suggestionWindow = null;
            }
            suggestionWindow = new SuggestionWindow(new SuggestionWindow.SuggestionCallback() {
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

                    String tmpSuggestion = suggestion;
                    final String text = getValue();
                    final StringBuilder builder = new StringBuilder(text);
                    final int min = Math.max(Math.min(start, end), 0);
                    if (min > 0) {
                        tmpSuggestion = " " + tmpSuggestion;
                    }
                    builder.replace(start, end + 1, tmpSuggestion);
                    setValue(builder.toString());
                    suggestionWindow.close();
                    suggestionWindow = null;
                }
            });
            UI.getCurrent().addWindow(suggestionWindow);
        }
    }

    private final class CtrlSpaceShortCutListener extends ShortcutListener {

        private static final long serialVersionUID = 1L;

        public CtrlSpaceShortCutListener() {
            super("Ctrl + Space", KeyCode.SPACEBAR, new int[] { ModifierKey.CTRL });
        }

        @Override
        public void handleAction(final Object sender, final Object target) {
            printOutSuggestions(AutocompleteTextfield.this.getValue(), false);
        }
    }

    private final class SpaceCloseWindowListener extends ShortcutListener {

        private static final long serialVersionUID = 1L;

        public SpaceCloseWindowListener() {
            super("Escape", KeyCode.ESCAPE, new int[] {});
        }

        @Override
        public void handleAction(final Object sender, final Object target) {
            if (suggestionWindow != null) {
                suggestionWindow.close();
                suggestionWindow = null;
            }
        }
    }
}
