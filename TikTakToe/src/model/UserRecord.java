package model;

import java.time.LocalDateTime;

public class UserRecord {
    private String username;
    private char userFigure;
    private LocalDateTime dateTime;
    private String result;

    public UserRecord(String username, char userFigure, LocalDateTime dateTime, String result) {
        this.username = username;
        this.userFigure = userFigure;
        this.dateTime = dateTime;
        this.result = result;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char getUserFigure() {
        return userFigure;
    }

    public void setUserFigure(char userFigure) {
        this.userFigure = userFigure;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
