package GUI.Together;

import Repository.RepositoryManager;
import Utilities.StaticVariables;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PersonProfileHeader extends VBox {
    private Label loggedUserNameLabel, loggedUserSurnameLabel, loggedUserBirthDateLabel, loggedUserMailLabel, loggedUserRoleLabel, activeAccountLabel,activateAccountCheckBoxLabel;

    private CheckBox activeAccountBox;

    private RepositoryManager repositoryManager;

    public PersonProfileHeader(RepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
        createComponents();
    }

    private void createComponents() {
        loggedUserNameLabel = new Label("Name: " + StaticVariables.loggedUser.getFirstName());
        loggedUserSurnameLabel = new Label("LastName: " + StaticVariables.loggedUser.getLastName());
        loggedUserBirthDateLabel = new Label("BirthDate: " + StaticVariables.loggedUser.getBirthDate());
        loggedUserMailLabel = new Label("Mail: " + StaticVariables.loggedUser.getMail());
        loggedUserRoleLabel = new Label("User System Role: " + StaticVariables.loggedUser.getSysRole());
        activeAccountLabel = new Label("Active Account: " + StaticVariables.loggedUser.getAccountActive());
        activateAccountCheckBoxLabel = new Label("Active Account ? ");
        activeAccountBox = new CheckBox();
        activeAccountBox.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            if(newValue){
                repositoryManager.getProgramUserRepository().deactivateAccount(StaticVariables.loggedUser);
                activeAccountLabel.setText("Active Account: " + StaticVariables.loggedUser.getAccountActive());
            }
            if(!newValue){
                repositoryManager.getProgramUserRepository().activateAccount(StaticVariables.loggedUser);
                activeAccountLabel.setText("Active Account: " + StaticVariables.loggedUser.getAccountActive());
            }
        });
        this.getChildren().addAll(loggedUserNameLabel, loggedUserSurnameLabel, loggedUserBirthDateLabel, loggedUserMailLabel, loggedUserRoleLabel, activeAccountLabel,activateAccountCheckBoxLabel,activeAccountBox);
    }
}
