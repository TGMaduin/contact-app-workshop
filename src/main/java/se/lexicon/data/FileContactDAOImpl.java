package se.lexicon.data;

import se.lexicon.exception.ContactStorageException;
import se.lexicon.exception.DuplicateContactException;
import se.lexicon.model.Contact;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileContactDAOImpl implements ContactDAO {

    private final Path filePath;

    public FileContactDAOImpl(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Contact> findAll() throws ContactStorageException {
        List<Contact> contacts = new ArrayList<>();

        if (!Files.exists(filePath)) {
            return contacts;
        }

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 2) {
                    contacts.add(new Contact(parts[0], parts[1]));
                }
            }

        } catch (IOException e) {
            throw new ContactStorageException("Could not read contacts.", e);
        }

        return contacts;
    }

    @Override
    public void save(Contact contact) throws ContactStorageException, DuplicateContactException {

        if (findByName(contact.getName()) != null) {
            throw new DuplicateContactException("A contact with that name already exists.");
        }

        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {

            writer.write(contact.toString());
            writer.newLine();

        } catch (IOException e) {
            throw new ContactStorageException("Could not save contact.", e);
        }
    }

    @Override
    public Contact findByName(String name) throws ContactStorageException {

        for (Contact contact : findAll()) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }

        return null;
    }
}