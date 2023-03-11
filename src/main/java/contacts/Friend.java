package contacts;

import lombok.*;

@Getter
@ToString(callSuper = true)
public class Friend extends Contact{

    private final String phoneNumber;


    public Friend(String name, String phoneNumber) {
        super(name);
        this.phoneNumber = phoneNumber;
    }
}
