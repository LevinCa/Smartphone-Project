package contacts;

import lombok.ToString;


/**
 * Class to create a Contact of type Business contact
 */
@ToString(callSuper = true)
public class BusinessContact extends Contact {

    private final String companyName;


    /**
     * All Arguments constructor
     * @param name Name of the business contact
     * @param companyName company name of the business contact
     */
    public BusinessContact(String name, String companyName) {
        super(name);
        this.companyName = companyName;
    }
}
