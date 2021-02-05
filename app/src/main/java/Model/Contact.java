package Model;

import java.util.ArrayList;

public class Contact {
   private String fname,email;
   private boolean  status;

    public Contact(String fname, String email, boolean status) {
        this.fname = fname;
        this.email = email;
        this.status = status;
    }

    public Contact() {
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    /*
    String address,dob,email;
    String fname,phone,role,status;

    public Contact() {
    }

    public Contact(String address, String dob, String email) {
        this.address = address;
        this.dob = dob;
        this.email = email;
    }

    public Contact(String address, String dob, String email, String fname, String phone, String role, String status) {
        this.address = address;
        this.dob = dob;
        this.email = email;
        this.fname = fname;
        this.phone = phone;
        this.role = role;
        this.status = status;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }*/
}
