import contacts.Contact;
import functionality.GPS;
import functionality.Radio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Smartphone implements Radio, GPS {

    private String model;
    private String manufacturer;
    List<Contact> contacts;


    public void addContact(Contact contact) {
        if (contact != null) {
            this.contacts.add(contact);
        }
    }

    public Contact getContact(int index) {
        return this.contacts.get(index);
    }

    public Contact getContactByName(String name) {
        return this.contacts.stream().filter(c -> c.getName().equals(name)).findFirst()
                .orElseThrow(() -> new IllegalStateException("Contact with name \"" + name + "\" not found"));
    }

    public void removeContactByName(String name) {
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
