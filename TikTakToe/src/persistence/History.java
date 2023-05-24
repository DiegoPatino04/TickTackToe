package persistence;

import model.UserRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class History {
    private List<UserRecord> userRecords;

    public History() {
        userRecords = new ArrayList<>();
    }

    public void addUserRecord(UserRecord userRecord) {
        userRecords.add(userRecord);
    }

    public void saveRecords() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("src/resources/history_game.txt", true))) {
            for (UserRecord userRecord : userRecords) {
                writer.println(userRecord.getUsername() + "," + userRecord.getUserFigure() + "," + userRecord.getDateTime() + "," + userRecord.getResult());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetUserRecords(){
        userRecords = new ArrayList<>();
    }

}
