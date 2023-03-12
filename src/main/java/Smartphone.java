import contacts.Contact;
import functionality.GPS;
import functionality.Radio;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * Class that is supposed to work like a smartphone
 */
public class Smartphone implements Radio, GPS {

    private List<Contact> contacts;


    /**
     * All Arguments constructor
     * @param contacts list of contacts
     */
    public Smartphone(List<Contact> contacts) {
        this.contacts = contacts.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * Method to add a contact to the contact list
     * @param contact to be added
     */
    public void addContact(Contact contact) {
        if (contact != null) {
            this.contacts.add(contact);
        }
    }

    /**
     * Searching for a contact in the list by its index
     * @param index of the contact to search
     * @return the contact with specified index
     */
    public Contact getContact(int index) {
        return this.contacts.get(index);
    }

    /**
     * Searching for a contact by their name
     * @param name of the contact to search
     * @return the first contact with specified name
     * @throws IllegalStateException if no contact with specified name exists
     */
    public Contact getContactByName(String name) throws IllegalStateException {
        return this.contacts.stream().filter(c -> c.getName().equals(name)).findFirst()
                .orElseThrow(() -> new IllegalStateException("Contact with name \"" + name + "\" not found"));
    }

    /**
     * Remove contact by their name from contact list
     * @param name of the contact to remove from the list
     * @throws IllegalStateException if contact with specified name does not exist
     */
    public void removeContactByName(String name) throws IllegalStateException {
        this.contacts.remove(this.getContactByName(name));
    }


    /**
     * Getting the position of a smartphone
     * @return "Köln" (hard coded)
     */
    @Override
    public String getPosition() {
        return "Köln";
    }

    /**
     * Starting the radio
     * @return true
     */
    @Override
    public boolean startRadio() {
        System.out.println("Radio started");
        return true;
    }

    /**
     * Stopping the radio
     * @return false
     */
    @Override
    public boolean stopRadio() {
        System.out.println("Radio stopped");
        return false;
    }

    /**
     * Getter for contact list
     * @return contact list
     */
    public List<Contact> getContacts() {
        return contacts;
    }
}
