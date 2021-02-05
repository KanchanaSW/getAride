package Model;

public class RequestCabD {
    String email,customerName,location,destination,dateAndtime,driverName,tp;
    String id;
    //email,tp,cusName,location,destination,name,dT

    public RequestCabD(String email, String tp, String customerName, String location, String destination, String driverName, String dateAndtime,String id) {
        this.email = email;
        this.tp = tp;
        this.customerName = customerName;
        this.location = location;
        this.destination = destination;
        this.driverName = driverName;
        this.dateAndtime = dateAndtime;
        this.id=id;
    }

    public RequestCabD() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDateAndtime() {
        return dateAndtime;
    }

    public void setDateAndtime(String dateAndtime) {
        this.dateAndtime = dateAndtime;
    }
}
