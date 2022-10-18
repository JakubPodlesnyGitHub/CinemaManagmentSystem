package GUI.ClientTabs;

import Model.Movie;
import Repository.RepositoryManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BookingTicketTab extends Tab {
    private RepositoryManager repositoryManager;
    private BorderPane borderPane;
    private DatePicker movieDatePicker;
    private Spinner<Integer> hours;
    private Spinner<Integer> minutes;
    private TableView<Movie> tableViewMovies;
    private Button search,proceedWithReservation;
    private HBox top;
    private VBox center;

    private TableColumn movieNameC, movieGenreC, movieDirectorC,choice;

    public BookingTicketTab(RepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
        createComponents();
        addComponentsToScreen();
        tabSettings();
    }

    private void createComponents() {
        top = new HBox();
        search = new Button("Search");
        proceedWithReservation = new Button("Proceed");
        borderPane = new BorderPane();
        movieDatePicker = new DatePicker();
        hours = new Spinner<>(1,24,1);
        minutes = new Spinner<>(1,60,1);
        top.getChildren().addAll(movieDatePicker,hours,minutes,search);
        borderPane.setTop(top);
        tableViewMovies = new TableView<>();
        tableViewMovies.setEditable(false);
        this.setContent(borderPane);
    }
    private void addComponentsToScreen(){
        search.setOnAction(actionEvent -> createTableView());
    }

    private void createTableView() {
        center = new VBox();
        movieNameC = new TableColumn("Movie Name");
        movieNameC.setCellValueFactory(new PropertyValueFactory<Movie, String>("movieName"));
        movieGenreC = new TableColumn("Movie Genre");
        movieGenreC.setCellValueFactory(new PropertyValueFactory<Movie, String>("genre"));
        movieDirectorC = new TableColumn("Movie Director");
        movieDirectorC.setCellValueFactory(new PropertyValueFactory<Movie, String>("director"));
        choice = new TableColumn("Choice");
        choice.setCellValueFactory(new PropertyValueFactory<Movie,Boolean>("ChosenMovie"));
        choice.setCellFactory(tableColumn -> new CheckBoxTableCell());
        tableViewMovies.getColumns().addAll(movieNameC, movieGenreC, movieDirectorC,choice);
        tableViewMovies.getItems().addAll(repositoryManager.getCinemaScrenningRepository().getMoviesByDateHours(movieDatePicker.getValue(),hours.getValue(),minutes.getValue()));
        center.getChildren().addAll(tableViewMovies,proceedWithReservation);
        borderPane.setCenter(center);
        proceedWithReservation.setOnAction(actionEvent -> {

        });
    }

    public void tabSettings() {
        this.setText("Booking Tickets");
    }

}
