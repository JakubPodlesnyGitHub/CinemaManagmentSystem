package GUI;

import Data.MockData;
import GUI.GuiMainViews.CashierPanel;
import GUI.GuiMainViews.ClientPanel;
import GUI.GuiMainViews.ManagerPanel;
import Repository.RepositoryManager;
import Utilities.CodeGeneration;
import Utilities.MailSender;
import Utilities.StaticVariables;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.mail.MessagingException;
import java.util.Optional;

public class StartWindow extends Application {

    private TextField loginField;
    private PasswordField passwordField;
    private Label loginLabel, passwordLabel;
    private TextFlow forgetPasswordFlow, registrationFlow;

    private Button loginButton;

    private Scene scene;
    private Stage stage;
    private VBox vBox;

    private RepositoryManager repositoryManager;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        activateDataBase();
        createFxComponents();
        addComponentsToScreen(stage);
        addListeners();
    }

    public void activateDataBase() {
        StandardServiceRegistry registry = null;
        SessionFactory sessionFactory = null;
        try {
            registry = new StandardServiceRegistryBuilder().configure().build();
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();

            repositoryManager = new RepositoryManager(sessionFactory);
            MockData mockData = new MockData(sessionFactory);
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        } finally {
            /*if (sessionFactory != null) {
                sessionFactory.close();
            }*/
        }
    }

    private void createFxComponents() {
        loginLabel = new Label("Login");
        passwordLabel = new Label("Password");
        loginField = new TextField();
        passwordField = new PasswordField();
        loginButton = new Button("Login");
        forgetPasswordFlow = new TextFlow(new Text("Doesn't remember the password ? "), new Hyperlink("FORGET PASSWORD"));
        registrationFlow = new TextFlow(new Text("Don't have an account? "), new Hyperlink("Click here"));
    }

    private void addComponentsToScreen(Stage primaryStage) {
        vBox = new VBox();
        vBox.getChildren().addAll(loginLabel, loginField, passwordLabel, passwordField, forgetPasswordFlow, registrationFlow, loginButton);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(5);
        this.scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addListeners() {
        loginButton.setOnAction(actionEvent -> {
            StaticVariables.loggedUser = repositoryManager.getProgramUserRepository().checkIfUserExists(loginField.getText(), passwordField.getText());
            if (StaticVariables.loggedUser != null) {
                generateCodeWithMail();
                createAlertWindowForCode();
            } else {
                Alert noUserDataAlert = new Alert(Alert.AlertType.WARNING, "There is no user with the login or password");
                noUserDataAlert.showAndWait();
            }
        });
    }

    private void generateCodeWithMail() {
        String newCode = CodeGeneration.generateCode(20);
        repositoryManager.getProgramUserRepository().setNewUserCode(StaticVariables.loggedUser, newCode);
        try {
            MailSender.createSENDMimeMessage(newCode);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private void createAlertWindowForCode() {
        TextInputDialog inputCodeDialog = new TextInputDialog();
        inputCodeDialog.setHeaderText("Please enter your code, that you received from th email");
        Optional<String> result = inputCodeDialog.showAndWait();
        if (result.isPresent()) {
            System.out.println(result.get());
            if (repositoryManager.getProgramUserRepository().getProgramUserByCode(result.get()) != null) {
                repositoryManager.getProgramUserRepository().activateAccount(StaticVariables.loggedUser);
                setProperStage();
            }
        }
    }

    public void setProperStage() {
        System.out.println("WCHODZI");
        System.out.println(StaticVariables.loggedUser.getSysRole().getRoleName());
        if (StaticVariables.loggedUser.getSysRole().getRoleName().equals("Client")) {
            stage.setScene(new Scene(new ClientPanel(repositoryManager)));
        } else if (StaticVariables.loggedUser.getSysRole().getRoleName().equals("Cashier")) {
            stage.setScene(new Scene(new CashierPanel()));
        } else if (StaticVariables.loggedUser.getSysRole().getRoleName().equals("Manager")) {
            stage.setScene(new Scene(new ManagerPanel()));
        }
        stage.show();
    }
}
