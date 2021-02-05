package Model;

public class LogStatus {
    String status,currentDateandTime;
    String userId;

    public LogStatus(String status, String currentDateandTime) {
        this.status = status;
        this.currentDateandTime = currentDateandTime;
    }

    public LogStatus() {
    }

    public String getUserId() {
        return userId;
    }

    public LogStatus(String status, String currentDateandTime, String userId) {
        this.status = status;
        this.currentDateandTime = currentDateandTime;
        this.userId = userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentDateandTime() {
        return currentDateandTime;
    }

    public void setCurrentDateandTime(String currentDateandTime) {
        this.currentDateandTime = currentDateandTime;
    }
}

