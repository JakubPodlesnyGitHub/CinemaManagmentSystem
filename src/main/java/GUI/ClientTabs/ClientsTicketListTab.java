package GUI.ClientTabs;

import Model.Booking;
import Model.Ticket;
import Utilities.StaticVariables;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.HashSet;
import java.util.Set;

public class ClientsTicketListTab extends Tab {
    private TableView<Ticket> tableView;
    private TableColumn ticketCodeC, seatC, creationDateC, ticketDescC;

    public ClientsTicketListTab() {
        tableView = new TableView<>();
        addCreateColumnsToTab();
        tabSettings();
        this.setContent(tableView);
    }

    public void addCreateColumnsToTab() {
        ticketCodeC = new TableColumn("Ticket Code");
        seatC = new TableColumn("Seat");
        creationDateC = new TableColumn("Creation Date");
        ticketDescC = new TableColumn("Ticket Description");
        tableView.getColumns().addAll(ticketCodeC, seatC, creationDateC, ticketDescC);
        tableView.getItems().addAll(getAllTickets());
        tableView.setEditable(false);
    }

    public void tabSettings() {
        this.setText("Tickets Client List");
    }

    public Set<Ticket> getAllTickets() {
        Set<Ticket> uniqueTicketsPersonSet = new HashSet<>();
        for (Booking personBooking : StaticVariables.loggedUser.getBookingList()) {
            uniqueTicketsPersonSet.addAll(personBooking.getTicketList());
        }
        uniqueTicketsPersonSet.addAll(StaticVariables.loggedUser.getTicketList());
        return uniqueTicketsPersonSet;
    }
}
