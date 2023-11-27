package Helpers;

public class IdTracking implements JsonIdentifiable{
    private int id = 0;
    private int accountId = 100000;

    public int getId() {
        this.id = id + 1;
        return id;
    }

    public int getAccountId() {
        this.accountId = accountId + 1;
        return accountId;
    }

    @Override
    public String getJsonId() {
        return "IdTracking";
    }

}
