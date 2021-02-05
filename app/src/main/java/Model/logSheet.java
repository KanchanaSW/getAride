package Model;

public class logSheet {
    String dEmail,dVehicalNo,After,Before;

    public logSheet(String dEmail, String dVehicalNo, String after, String before) {
        this.dEmail = dEmail;
        this.dVehicalNo = dVehicalNo;
        After = after;
        Before = before;
    }

    public logSheet() {
    }

    public String getdEmail() {
        return dEmail;
    }

    public void setdEmail(String dEmail) {
        this.dEmail = dEmail;
    }

    public String getdVehicalNo() {
        return dVehicalNo;
    }

    public void setdVehicalNo(String dVehicalNo) {
        this.dVehicalNo = dVehicalNo;
    }

    public String getAfter() {
        return After;
    }

    public void setAfter(String after) {
        After = after;
    }

    public String getBefore() {
        return Before;
    }

    public void setBefore(String before) {
        Before = before;
    }
}
