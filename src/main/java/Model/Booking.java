package Model;

import Utilities.CodeGeneration;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Booking")
@Access(AccessType.FIELD)
public class Booking {
    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "idBooking", nullable = false)
    private Long idBooking;
    @Column(name = "BookingCode", nullable = false, unique = true, length = 15)
    private String bookingCode;
    @Column(name = "BookingCreationDate", nullable = false)
    private Date creationDate;
    @Column(name = "BookingNumberOfSeats", nullable = false)
    private int numberOfSeats;

    @OneToMany(mappedBy = "booking",fetch = FetchType.EAGER)
    private List<Ticket> ticketList = new ArrayList<>();

    @ManyToOne
    private ProgramUser user;

    public Booking(int numberOfSeats) {
        setBookingCode(CodeGeneration.generateCode(15));
        setCreationDate(Date.valueOf(LocalDate.now()));
        setNumberOfSeats(numberOfSeats);
    }

    public Booking() {
    }

    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Long getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(Long idBooking) {
        this.idBooking = idBooking;
    }

    //connections

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public ProgramUser getUser() {
        return user;
    }

    public void addTicketsToCollection(Ticket newTicket) {
        if (!ticketList.contains(newTicket)) {
            ticketList.add(newTicket);
        }
    }

    public void removeTicketsFromCollection(Ticket ticketToRemove) {
        if (ticketList.contains(ticketToRemove)) {
            ticketList.remove(ticketToRemove);
        }
    }

    public void setUser(ProgramUser newUser) {
        if (newUser.equals(this.user))
            return;
        if (this.user != null)
            this.user.removeBookingFromCollection(this);
        this.user = newUser;
        newUser.addBookingToCollection(this);
    }

    @Override
    public String toString() {
        return getBookingCode();
    }
}
