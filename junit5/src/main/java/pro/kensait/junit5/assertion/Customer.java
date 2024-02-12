package pro.kensait.junit5.assertion;

public class Customer {
    private final int id;
    private final String name;
    private final CustomerType customerType;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
        this.customerType = CustomerType.GOLD; // デフォルト値
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public static enum CustomerType {
        GENERAL, GOLD
    }
}