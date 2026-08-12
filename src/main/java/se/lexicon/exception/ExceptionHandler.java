package se.lexicon.exception;

import se.lexicon.view.ContactView;

public class ExceptionHandler {

    public static void handle(Exception e, ContactView view) {
        switch (e) {
            case IllegalArgumentException ex ->
                    view.displayError("Invalid input: " + ex.getMessage());

            case DuplicateContactException ex ->
                    view.displayError("Duplicate contact: " + ex.getMessage());

            case ContactStorageException ex ->
                    view.displayError("Storage error: " + ex.getMessage());

            default ->
                    view.displayError("Unexpected error: " + e.getMessage());
        }
    }
}