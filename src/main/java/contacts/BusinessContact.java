package contacts;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class BusinessContact extends Contact {

    private String companyName;


    public BusinessContact(String name, String companyName) {
        super(name);
        this.companyName = companyName;
    }
}
