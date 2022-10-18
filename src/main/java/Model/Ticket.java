package Model;

import Utilities.CodeGeneration;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;
import java.time.LocalDate;

@Entity(name = "Ticket")
@Access(AccessType.FIELD)
public class Ticket {
    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "idTicket", nullable = false)
    private Long idTicket;
    @Column(name = "TicketCode", nullable = false, unique = true, length = 15)
    private String ticketCode;
    @Column(name = "Seat", nullable = false)
    private int seat;
    @Column(name = "CreationDate", nullable = false)
    private LocalDate creationDate;
    @Column(name = "TicketDescription", nullable = false, length = 2000)
    private String desc;

    @ManyToOne
    private Booking booking;
    @ManyToOne
    private ProgramUser owner;
    @ManyToOne
    private CinemaScrenning cinemaScreening;

    public Ticket(int seat, String desc) {
        setTicketCode(CodeGeneration.generateCode(15));
        setSeat(seat);
        setCreationDate(LocalDate.now());
        setDesc(desc);
    }

    public Ticket() {

    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking newBooking) {
        if (newBooking.equals(this.booking))
            return;
        if (this.booking != null)
            this.booking.removeTicketsFromCollection(this);
        this.booking = newBooking;
        newBooking.addTicketsToCollection(this);
    }

    public ProgramUser getUser() {
        return owner;
    }

    public void setUser(ProgramUser newProgramUser) {
        if (newProgramUser.equals(this.owner))
            return;
        if (this.owner != null)
            this.owner.removeTicketFormCollection(this);
        this.owner = newProgramUser;
        newProgramUser.addToTicketToCollection(this);

    }

    public CinemaScrenning getCinemaScrenning() {
        return cinemaScreening;
    }

    public void setCinemaScrenning(CinemaScrenning newCinemaScreening) {
        if (newCinemaScreening.equals(this.cinemaScreening))
            return;
        if (this.cinemaScreening != null)
            this.cinemaScreening.removeTicketFromCollection(this);
        this.cinemaScreening = newCinemaScreening;
        newCinemaScreening.addTicketToCollection(this);
    }

    public Long getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Long idTicket) {
        this.idTicket = idTicket;
    }
}
