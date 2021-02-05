package Model;

public class DriverSche {
    String dName,dDetails,timeSlot1,timeSlot2,timeSlot3,timeSlot4;
    public DriverSche(){

    }

    public DriverSche(String dName, String dDetails, String timeSlot1, String timeSlot2, String timeSlot3) {
        this.dName = dName;
        this.dDetails = dDetails;
        this.timeSlot1 = timeSlot1;
        this.timeSlot2 = timeSlot2;
        this.timeSlot3 = timeSlot3;
    }

    public DriverSche(String dName, String dDetails, String timeSlot1, String timeSlot2, String timeSlot3, String timeSlot4) {
        this.dName = dName;
        this.dDetails = dDetails;
        this.timeSlot1 = timeSlot1;
        this.timeSlot2 = timeSlot2;
        this.timeSlot3 = timeSlot3;
        this.timeSlot4 = timeSlot4;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getdDetails() {
        return dDetails;
    }

    public void setdDetails(String dDetails) {
        this.dDetails = dDetails;
    }

    public String getTimeSlot1() {
        return timeSlot1;
    }

    public void setTimeSlot1(String timeSlot1) {
        this.timeSlot1 = timeSlot1;
    }

    public String getTimeSlot2() {
        return timeSlot2;
    }

    public void setTimeSlot2(String timeSlot2) {
        this.timeSlot2 = timeSlot2;
    }

    public String getTimeSlot3() {
        return timeSlot3;
    }

    public void setTimeSlot3(String timeSlot3) {
        this.timeSlot3 = timeSlot3;
    }

    public String getTimeSlot4() {
        return timeSlot4;
    }

    public void setTimeSlot4(String timeSlot4) {
        this.timeSlot4 = timeSlot4;
    }
}
