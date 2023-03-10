package contacts;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class Friend extends Contact{

    private String phoneNumber;


    public Friend(String name, String phoneNumber) {
        super(name);
        this.phoneNumber = phoneNumber;
    }
}
