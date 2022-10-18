package GUI.ClientTabs;

import Model.Movie;
import Repository.RepositoryManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class BuyingTicketsTab extends Tab {
    private RepositoryManager repositoryManager;
    private BorderPane borderPane;
    private DatePicker movieDatePicker;
    private Spinner<Integer> hours;
    private Spinner<Integer> minutes;
    private TableView<Movie> tableViewMovies;
    private Button search,proceedWithBuying;
    private HBox top,center;

    private TableColumn movieNameC, movieGenreC, movieDirectorC,choice;

    public BuyingTicketsTab(RepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
        createComponents();
        addComponentsToScreen();
        tabSettings();
    }

    private void createComponents() {
        top = new HBox();
        search = new Button("Search");
        borderPane = new BorderPane();
        movieDatePicker = new DatePicker();
        hours = new Spinner<>(1,24,1);
        minutes = new Spinner<>(1,60,1);
        tableViewMovies = new TableView<>();
        tableViewMovies.setEditable(false);
    }
    private void addComponentsToScreen(){
        top.getChildren().addAll(movieDatePicker,hours,minutes,search);
        borderPane.setTop(top);
        search.setOnAction(actionEvent -> createTableView());
        this.setContent(borderPane);
    }
    private void createTableView() {
        center = new HBox();
        movieNameC = new TableColumn("Movie Name");
        movieGenreC = new TableColumn("Movie Genre");
        movieDirectorC = new TableColumn("Movie Director");
        choice = new TableColumn("Choice");
        tableViewMovies.getColumns().addAll(movieNameC, movieGenreC, movieDirectorC,choice);
        tableViewMovies.getItems().addAll(repositoryManager.getCinemaScrenningRepository().getMoviesByDateHours(movieDatePicker.getValue(),hours.getValue(),minutes.getValue()));
        center.getChildren().addAll(tableViewMovies,proceedWithBuying);
        borderPane.setCenter(center);
        proceedWithBuying.setOnAction(actionEvent -> {

        });
    }

    public void tabSettings() {
        this.setText("Buying Tickets");
    }
}
