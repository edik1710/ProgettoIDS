package it.unicam.cs.ids.localplatform.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class represents a contest.
 */
public class Contest {
    private final String objective;
    private final String title;
    private final Date startDate;
    private final Date endDate;
    private final List<Content> contents;

    public Contest(String objective, String title, Date startDate, Date endDate) {
        if (startDate.after(endDate))
            throw new IllegalArgumentException("The start date cannot be future relative to the end date.");
        if (startDate.before(new Date()) || endDate.before(new Date()))
            throw new IllegalArgumentException("The start and end date cannot be in the past.");

        this.objective = objective;
        this.title = title;
        this.contents = new ArrayList<>();
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * This method allows to add a content to the list of contents.
     *
     * @param content The content to be added to the list of contents.
     */
    public void addContent(Content content) {
        this.contents.add(content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contest that = (Contest) o;
        return this.title.equals(that.title) && this.objective.equals(that.objective);
    }

    public Date getEndDate() {
        return endDate;
    }

    public List<Content> getContents() {
        return contents;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Concorso creato il " + sdf.format(startDate) +
                ".\nTitolo: " + title +
                "\nObiettivo: " + objective +
                "\nData di fine: " + sdf.format(endDate) + "\n";
    }

}
