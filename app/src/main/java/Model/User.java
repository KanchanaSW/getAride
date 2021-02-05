package Model;



public class User {

    String fname,password,address,phone,email,dob,role,nic;
    boolean status;
    String name;

    public User() {
    }

    public User( String email,String fname ,String nic) {

        this.email = email;
        this.fname = fname;
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public User(String fname, String address, String phone, String email, String dob, String role) {

        this.fname = fname;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.dob = dob;
        this.role = role;
    }

    public User(String fname, String address, String phone, String email, String dob, String role, boolean status) {
        this.fname = fname;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.dob = dob;
        this.role = role;
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    /*
    public User(String Fname, String Address, String Phone, String Email) {
        this.Fname = Fname;
        this.Address = Address;
        this.Phone = Phone;
        this.Email = Email;
    }



    public User(String Fname, String Address, String Phone, String Email, String Dob) {
        this.Fname = Fname;
        this.Address = Address;
        this.Phone = Phone;
        this.Email = Email;
        this.Dob = Dob;
    }

    public User(String Fname, String Address, String Phone, String Email, String Dob, String Role) {
        this.Fname = Fname;
        this.Address = Address;
        this.Phone = Phone;
        this.Email = Email;
        this.Dob = Dob;
        this.Role = Role;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

 */
}
