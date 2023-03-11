package contacts;

import lombok.ToString;

@ToString(callSuper = true)
public class BusinessContact extends Contact {

    private final String companyName;


    public BusinessContact(String name, String companyName) {
        super(name);
        this.companyName = companyName;
    }
}
