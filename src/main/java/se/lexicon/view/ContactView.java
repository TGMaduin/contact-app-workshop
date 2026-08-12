package se.lexicon.view;

import se.lexicon.model.Contact;

import java.util.List;

public class ContactView {

    public String getUserInput(String prompt) {
        return IO.readln(prompt);
    }

    public void displayMenu() {
        IO.println("""
                ===== Contact App =====
                1. Show contacts
                2. Add contact
                3. Find contact
                0. Exit
                """);
    }

    public void displayContacts(List<Contact> contacts) {
        if (contacts.isEmpty()) {
            IO.println("No contacts found.");
            return;
        }

        for (Contact contact : contacts) {
            IO.println(contact);
        }
    }

    public void displayMessage(String message) {
        IO.println(message);
    }

    public void displayError(String message) {
        IO.println("Error: " + message);
    }
}