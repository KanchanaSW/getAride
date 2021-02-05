package Model;

public class RideStatus {
    String customerName,phone,mileage,price;

    public RideStatus(String customerName, String phone, String mileage, String price) {
        this.customerName = customerName;
        this.phone = phone;
        this.mileage = mileage;
        this.price = price;
    }

    public RideStatus() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
