package se.lexicon.controller;

import se.lexicon.data.ContactDAO;
import se.lexicon.model.Contact;
import se.lexicon.view.ContactView;
import se.lexicon.exception.ExceptionHandler;

public class ContactController {

    private final ContactDAO contactDAO;
    private final ContactView contactView;

    public ContactController(ContactDAO contactDAO, ContactView contactView) {
        this.contactDAO = contactDAO;
        this.contactView = contactView;
    }

    public void run() {
        boolean running = true;

        while (running) {
            contactView.displayMenu();

            String choice = contactView.getUserInput("Choose: ");

            try {
                switch (choice) {
                    case "1" -> contactView.displayContacts(contactDAO.findAll());

                    case "2" -> addContact();

                    case "3" -> findContact();

                    case "0" -> running = false;

                    default -> contactView.displayError("Invalid menu option.");
                }

            } catch (Exception e) {
                ExceptionHandler.handle(e, contactView);
            }
        }
    }

    private void addContact() throws Exception {
        String name = contactView.getUserInput("Name: ");

        String phone = contactView.getUserInput("Phone number: ");

        Contact contact = new Contact(name, phone);

        contactDAO.save(contact);

        contactView.displayMessage("Contact saved.");
    }

    private void findContact() throws Exception {
        String name =
                contactView.getUserInput("Name to search for: ");

        Contact contact = contactDAO.findByName(name);

        if (contact == null) {
            contactView.displayMessage("Contact not found.");
        }
        else {
            contactView.displayMessage(contact.toString());
        }
    }
}