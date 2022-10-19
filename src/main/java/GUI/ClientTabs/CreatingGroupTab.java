package GUI.ClientTabs;

import Repository.RepositoryManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class CreatingGroupTab extends Tab {
    private BorderPane pane;
    private Label groupAddingLabel, groupNameL, groupDescriptionL;
    private TextField nameField, groupField;
    private Button addNewGroupButton;
    private RepositoryManager repositoryManager;

    public CreatingGroupTab(RepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
        createComponents();
        createActionListener();
        addElementsToTab();
        tabSettings();
    }

    private void createComponents() {
        pane = new BorderPane();
        groupNameL = new Label("Group Name");
        groupAddingLabel = new Label("Adding New Group");
        groupDescriptionL = new Label("Group Description");
        nameField = new TextField();
        groupField = new TextField();
        addNewGroupButton = new Button("Add New Group");
    }

    private void addElementsToTab(){

    }

    private void createActionListener(){
        addNewGroupButton.setOnAction(actionEvent -> {

        });
    }

    private void tabSettings() {
        this.setText("Creating New Group");
    }


}
