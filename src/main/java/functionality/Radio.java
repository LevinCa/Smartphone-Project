package functionality;


/**
 * Interface to turn on/off the radio of a smartphone
 */
public interface Radio {

    /**
     * Method to start the radio
     * @return status of the radio
     */
    boolean startRadio();

    /**
     * Method to stop the radio
     * @return status of the radio
     */
    boolean stopRadio();
}
