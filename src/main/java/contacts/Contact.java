package contacts;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * Abstract class inherited by contacts.Friend and contacts.BusinessContact
 */
@Data
@AllArgsConstructor
public abstract class Contact {
    protected String name;
}
