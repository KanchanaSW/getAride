package Model;

public class Driver {
   public String dName,dNic,dEmail,dPhone,dAddress;

    public Driver() {
    }



    public Driver(String dName, String dNic, String dEmail, String dPhone, String dAddress) {
        this.dName = dName;
        this.dNic = dNic;
        this.dEmail = dEmail;
        this.dPhone = dPhone;
        this.dAddress = dAddress;
    }
    public Driver(String dName, String dEmail, String dNic) {
        this.dName = dName;
        this.dEmail = dEmail;
        this.dNic = dNic;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getdNic() {
        return dNic;
    }

    public void setdNic(String dNic) {
        this.dNic = dNic;
    }

    public String getdEmail() {
        return dEmail;
    }

    public void setdEmail(String dEmail) {
        this.dEmail = dEmail;
    }

    public String getdPhone() {
        return dPhone;
    }

    public void setdPhone(String dPhone) {
        this.dPhone = dPhone;
    }

    public String getdAddress() {
        return dAddress;
    }

    public void setdAddress(String dAddress) {
        this.dAddress = dAddress;
    }
}
