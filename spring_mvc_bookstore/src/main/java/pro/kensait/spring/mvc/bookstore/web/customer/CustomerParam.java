package pro.kensait.spring.mvc.bookstore.web.customer;

import java.time.LocalDate;

public class CustomerParam {
    // 顧客番号
    private Integer customerId;
    // 顧客名
    private String customerName;
    // Eメールアドレス
    private String email;
    // パスワード 
    private String password;
    // 生年月日
    private LocalDate birthday;
    // 住所
    private String address;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "CustomerForm [customerId=" + customerId + ", customerName=" + customerName
                + ", email=" + email + ", password=" + password + ", birthday=" + birthday
                + ", address=" + address + "]";
    }
}