package ib.zpo2.L1;

// Create a class representing the Customer object - it should contain all the fields
// that can be separated from the customer token (use appropriate types)
public class Customer {
    private String countryCode;
    private String id;
    private String externalId;
    private String enableServices;

    public Customer(String countryCode, String id, String externalId, String enableServices) {
        this.countryCode = countryCode;
        this.id = id;
        this.externalId = externalId;
        this.enableServices = enableServices;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getEnableServices() {
        return enableServices;
    }

    public void setEnableServices(String enableServices) {
        this.enableServices = enableServices;
    }
}
