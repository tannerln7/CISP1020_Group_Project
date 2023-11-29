package Helpers;
/**
 * The IdTracking class is used to generate unique IDs for different entities in the system.
 * It implements the JsonIdentifiable interface.
 */
public class IdTracking extends ObjectJson implements JsonIdentifiable{
    private int id = 0;
    private int accountId = 100000;

    /**
     * Retrieves the next ID for receipt objects and increments the current ID.
     *
     * @return The next ID for receipt objects.
     */
    public int getId() {
        this.id = id + 1;
        return id;
    }

    /**
     * Retrieves the next ID for customer objects and increments the current account ID.
     *
     * @return The next ID for customer objects.
     */
    public int getAccountId() {
        this.accountId = accountId + 1;
        return accountId;
    }

    /**
     * Overrides the `getJsonId()` method to generate a JSON-compatible ID for the IdTracking object.
     *
     * @return The JSON-compatible ID for the IdTracking object.
     */
    @Override
    public String getJsonId() {
        return "IdTracking";
    }

}
