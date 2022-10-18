package GUI.GuiMainViews;

import GUI.ClientTabs.BuyingTicketsTab;
import GUI.Together.BookingTicketsViewTab;
import GUI.Together.PersonProfileHeader;
import GUI.ClientTabs.ClientsTicketListTab;
import GUI.ClientTabs.BookingTicketTab;
import Repository.RepositoryManager;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class ClientPanel extends BorderPane {
    private BuyingTicketsTab buyingTicketsTab;
    private ClientsTicketListTab clientsTicketListTab;
    private PersonProfileHeader personProfileHeader;
    private BookingTicketTab bookingTicketTab;
    private BookingTicketsViewTab bookingTicketsViewTab;
    private RepositoryManager repositoryManager;

    private TabPane tabPane;

    public ClientPanel(RepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
        tabPane = new TabPane();
        buyingTicketsTab = new BuyingTicketsTab(repositoryManager);
        clientsTicketListTab = new ClientsTicketListTab();
        personProfileHeader = new PersonProfileHeader(repositoryManager);
        bookingTicketTab = new BookingTicketTab(repositoryManager);
        bookingTicketsViewTab = new BookingTicketsViewTab(repositoryManager);
        addTabsToPane();
        this.setTop(personProfileHeader);
        this.setCenter(tabPane);
    }

    private void addTabsToPane() {
        tabPane.getTabs().add(buyingTicketsTab);
        tabPane.getTabs().add(clientsTicketListTab);
        tabPane.getTabs().add(bookingTicketTab);
        tabPane.getTabs().add(bookingTicketsViewTab);
    }
}
