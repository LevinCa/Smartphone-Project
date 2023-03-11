import contacts.Contact;
import functionality.GPS;
import functionality.Radio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@ToString
public class Smartphone implements Radio, GPS {

    private String model;
    private String manufacturer;
    List<Contact> contacts;

    public Smartphone(String model, String manufacturer, List<Contact> contacts) {
        this.model = model;
        this.manufacturer = manufacturer;
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
        System.out.println("functionality.Radio started");
        return true;
    }

    @Override
    public boolean stopRadio() {
        System.out.println("functionality.Radio stopped");
        return false;
    }
}
