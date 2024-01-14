package it.unicam.cs.ids.localplatform.model;

public class Tourist extends User {
    private int reportedContents;

    public Tourist(String name, String surname, String email, String password, String address, String cf) {
        super(name, surname, email, password, address, cf);
        this.reportedContents = 0;
    }

    public int getReportedContents() {
        return reportedContents;
    }
}
