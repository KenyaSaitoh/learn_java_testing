package pro.kensait.mockito.customer;

import java.util.Objects;

/*
 * 顧客を表すクラス
 */
public class Customer {
    private final int id;
    private final String name;
    private final CustomerType customerType;

    public Customer(int id, String name, CustomerType customerType) {
        this.id = id;
        this.name = name;
        this.customerType = customerType;
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

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", customerType=" + customerType + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerType, id, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        return customerType == other.customerType && id == other.id
                && Objects.equals(name, other.name);
    }
}