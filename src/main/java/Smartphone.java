import contacts.Contact;
import functionality.GPS;
import functionality.Radio;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class Smartphone implements Radio, GPS {

    List<Contact> contacts;

    public Smartphone(List<Contact> contacts) {
        this.contacts = contacts.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    public void addContact(Contact contact) {
        if (contact != null) {
            this.contacts.add(contact);
        }
    }

    public Contact getContact(int index) {
        return this.contacts.get(index);
    }

    public Contact getContactByName(String name) throws IllegalStateException {
        return this.contacts.stream().filter(c -> c.getName().equals(name)).findFirst()
                .orElseThrow(() -> new IllegalStateException("Contact with name \"" + name + "\" not found"));
    }

    public void removeContactByName(String name) throws IllegalStateException {
        this.contacts.remove(this.getContactByName(name));
    }


    @Override
    public String getPosition() {
        return "Köln";
    }

    @Override
    public boolean startRadio() {
        System.out.println("Radio started");
        return true;
    }

    @Override
    public boolean stopRadio() {
        System.out.println("Radio stopped");
        return false;
    }

    public List<Contact> getContacts() {
        return contacts;
    }
}
