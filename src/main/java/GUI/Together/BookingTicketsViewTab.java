package GUI.Together;

import Model.Booking;
import Model.Ticket;
import Repository.RepositoryManager;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.time.LocalDate;

public class BookingTicketsViewTab extends Tab {
    private Label bookingChoiceLabel;
    private ComboBox<Booking> bookingComboBox;
    private TableView<Ticket> ticketsBooking;
    private Button search;
    private BorderPane borderPane;

    private TableColumn ticketCodeC, ticketSeatC, creationDateC, ticketDescC;
    private RepositoryManager repositoryManager;
    private HBox top;

    public BookingTicketsViewTab(RepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
        createComponents();
        tabSettings();
    }

    private void createComponents() {
        borderPane = new BorderPane();
        top = new HBox();
        search = new Button("Search Tickets");
        bookingChoiceLabel = new Label("Choose booking ");
        bookingComboBox = new ComboBox<>();
        System.out.println(repositoryManager.getBookingRepository().getBookings());
        bookingComboBox.getItems().addAll(repositoryManager.getBookingRepository().getBookings());
        top.getChildren().addAll(bookingChoiceLabel, bookingComboBox, search);
        borderPane.setTop(top);
        search.setOnAction(actionEvent -> createTableView());
        this.setContent(borderPane);
    }

    private void createTableView() {
        ticketCodeC = new TableColumn("Ticket Code");
        ticketCodeC.setCellValueFactory(new PropertyValueFactory<Ticket,String>("ticketCode"));
        ticketSeatC = new TableColumn("Ticket Seat");
        ticketSeatC.setCellValueFactory(new PropertyValueFactory<Ticket,Integer>("seat"));
        creationDateC = new TableColumn("Creation Date");
        creationDateC.setCellValueFactory(new PropertyValueFactory<Ticket, LocalDate>("creationDate"));
        ticketDescC = new TableColumn("Ticket Desc");
        ticketDescC.setCellValueFactory(new PropertyValueFactory<Ticket,String>("desc"));
        ticketsBooking = new TableView<>();
        ticketsBooking.getColumns().addAll(ticketCodeC,ticketSeatC,creationDateC,ticketDescC);
        ticketsBooking.getItems().addAll(bookingComboBox.getValue().getTicketList());
        borderPane.setCenter(ticketsBooking);
    }

    private void tabSettings() {
        this.setText("Show Tickets For Specific Booking");
    }

//    private void addBookingsToComboBox(){
//        for (Booking booking : repositoryManager.getBookingRepository().getBookings()){
//            bookingComboBox.getItems().add(booking)
//        }
//    }
}
