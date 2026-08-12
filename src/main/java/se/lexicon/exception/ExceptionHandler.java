package se.lexicon.exception;

import se.lexicon.view.ContactView;

public class ExceptionHandler {

    public static void handle(
            Exception e,
            ContactView view
    ) {
        if (e instanceof IllegalArgumentException) {
            view.displayError(
                    "Invalid input: " + e.getMessage()
            );

        } else if (e instanceof DuplicateContactException) {
            view.displayError(
                    "Duplicate contact: " + e.getMessage()
            );

        } else if (e instanceof ContactStorageException) {
            view.displayError(
                    "Storage error: " + e.getMessage()
            );

        } else {
            view.displayError(
                    "Unexpected error: " + e.getMessage()
            );
        }
    }
}