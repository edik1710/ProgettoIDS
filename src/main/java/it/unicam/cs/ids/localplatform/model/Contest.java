package it.unicam.cs.ids.localplatform.model;

import java.util.ArrayList;
import java.util.List;

public class Contest {
    private String objective;

    /**
     * Contenuti proposti per il contest
     */
    private List<Content> contents;

    public Contest(String objective) {
        this.objective = objective;
        this.contents = new ArrayList<>(); // Quando viene creato un Contest, la lista di contenuti proposti è vuota
    }
}
