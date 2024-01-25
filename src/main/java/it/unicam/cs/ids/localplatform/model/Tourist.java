package it.unicam.cs.ids.localplatform.model;

import it.unicam.cs.ids.localplatform.MunicipalTerritory;

/**
 * This class represents a tourist.<br><br>
 * Tourists can query the knowledge base in order to retrieve information about the territory.
 * If they believe that a {@link Content} is not suitable, they can also report the content to the {@link Curator}.
 */
public class Tourist extends User {
    private int numReports;
    public Tourist(String name, String surname, String email, String password, MunicipalTerritory residence, String cf) {
        super(name, surname, email, password, residence, cf);
        this.numReports = 0;
    }

    /**
     * This method allows the tourist to report a content.
     *
     * @param content The content to be reported.
     */
    public void reportContent(Content content) {
        this.numReports++;
        content.reportContent();
    }
    public int getNumReports() {
        return numReports;
    }

}
