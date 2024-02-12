package it.unicam.cs.ids.localplatform.util;

import it.unicam.cs.ids.localplatform.model.Animator;
import it.unicam.cs.ids.localplatform.model.Contest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents an animator handler.
 */
public class AnimatorHandler {
    @FXML
    public TextField ContestObjective;
    @FXML
    public TextField ContestTitle;
    @FXML
    public DatePicker ContestStartDate;
    @FXML
    public DatePicker ContestEndDate;
    @FXML
    private ListView<String> ContestList;

    private final Animator animator;

    public AnimatorHandler() {
        this.animator = (Animator) LoginController.getCurrentUser();
    }

    /**
     * This method allows the animator to create a contest.
     */
    public void createContest(ActionEvent actionEvent) {
        String title = ContestTitle.getText();
        String objective = ContestObjective.getText();

        Date startDate = Date.from(ContestStartDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(ContestEndDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

        this.animator.proposeContest(objective, title, startDate, endDate);
    }

    public void getContests(ActionEvent actionEvent) {
        ContestList.getItems().clear();
        // Converti l'insieme di contest in una lista di stringhe
        List<String> contests = this.animator.getResidence().getContests().stream()
                .map(Contest::toString)
                .collect(Collectors.toList());

        // Aggiungi tutti i contest alla lista ContestList
        ContestList.getItems().addAll(contests);
    }
}
