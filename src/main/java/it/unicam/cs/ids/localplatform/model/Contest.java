package it.unicam.cs.ids.localplatform.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Contest {
    private String objective;
    private String title;
    private Date startDate;
    private Date endDate;
    private List<Content> contents;

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
        return this.title.equals(that.title) && this.objective.equals(that.title);
    }

    public Date getEndDate() {
        return endDate;
    }

}
