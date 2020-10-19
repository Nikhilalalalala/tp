package com.eva.ui;

import com.eva.commons.core.LogsCenter;
import com.eva.model.person.applicant.Applicant;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

import java.util.logging.Logger;

/**
 * Panel containing the list of staffs.
 */
public class ApplicantListPanel extends UiPart<Region> {
    private static final String FXML = "StaffListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Applicant> applicantListView;

    /**
     * Creates a {@code StaffListPanel} with the given {@code ObservableList}.
     */
    public ApplicantListPanel(ObservableList<Applicant> applicantList) {
        super(FXML);
        applicantListView.setItems(applicantList);
        applicantListView.setCellFactory(listView -> new ApplicantListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Staff} using a {@code StaffCard}.
     */
    class ApplicantListViewCell extends ListCell<Applicant> {
        @Override
        protected void updateItem(Applicant applicant, boolean empty) {
            super.updateItem(applicant, empty);

            if (empty || applicant == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ApplicantCard(applicant, getIndex() + 1).getRoot());
            }
        }
    }

}

