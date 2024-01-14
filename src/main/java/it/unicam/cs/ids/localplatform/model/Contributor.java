package it.unicam.cs.ids.localplatform.model;

import java.util.LinkedList;
import java.util.Queue;

public class Contributor extends User {
    private Queue<Info> pendingSubmits;
    private ContributorData contributorInfo;

    public Contributor(String name, String surname, String email, String password, String address, String cf) {
        super(name, surname, email, password, address, cf);
        this.pendingSubmits = new LinkedList<>();
        this.contributorInfo = null; // Da completare
    }

    public ContributorData getContributorInfo() {
        return contributorInfo;
    }
}
