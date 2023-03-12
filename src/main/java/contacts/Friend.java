package contacts;

import lombok.*;


/**
 * Class to create a Contact of type Friend
 */
@Getter
@ToString(callSuper = true)
public class Friend extends Contact{

    private final String phoneNumber;


    /**
     * All Arguments Constructor
     * @param name Name of the friend
     * @param phoneNumber phone number of the friend
     */
    public Friend(String name, String phoneNumber) {
        super(name);
        this.phoneNumber = phoneNumber;
    }
}
