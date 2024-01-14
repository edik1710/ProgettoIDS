package it.unicam.cs.ids.localplatform.model;

public class AuthorizedContributor extends User {
    private ContributorData contributorInfo;

    public AuthorizedContributor(String name, String surname, String email, String password, String address, String cf) {
        super(name, surname, email, password, address, cf);
        this.contributorInfo = null; // Da completare
    }

    public ContributorData getContributorInfo() {
        return contributorInfo;
    }
}
