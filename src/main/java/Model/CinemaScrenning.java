package Model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "CinemaScrenning")
@Access(AccessType.FIELD)
public class CinemaScrenning {
    @Id
    @Column(name = "idCinemaScrenning", nullable = false)
    @GeneratedValue(generator = "increment", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long idCinemaScrenning;

    @Column(name = "ScrenningDateHours", nullable = false)
    private LocalDateTime screnningDateHour;

    @ManyToOne
    private Movie movie;

    @OneToMany(mappedBy = "cinemaScreening")
    private List<Ticket> ticketList = new ArrayList<>();


    public CinemaScrenning(LocalDateTime screnningDateHour) {
        this.screnningDateHour = screnningDateHour;
    }

    public CinemaScrenning() {

    }

    public Long getIdCinemaScrenning() {
        return idCinemaScrenning;
    }

    public void setIdCinemaScrenning(Long idCinemaScrenning) {
        this.idCinemaScrenning = idCinemaScrenning;
    }

    public LocalDateTime getScrenningDateHour() {
        return screnningDateHour;
    }

    public void setScrenningDateHour(LocalDateTime screnningDateHour) {
        this.screnningDateHour = screnningDateHour;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie newMovie) {
        if (newMovie.equals(this.movie))
            return;
        if (this.movie != null)
            this.movie.removeCinemaScreeningFromCollection(this);
        this.movie = newMovie;
        newMovie.addCinemaScreening(this);
    }

    public void addTicketToCollection(Ticket newTicket) {
        if (!ticketList.contains(newTicket)) {
            ticketList.add(newTicket);
            newTicket.setCinemaScrenning(this);
        }
    }

    public void removeTicketFromCollection(Ticket ticketToRemove) {
        if (ticketList.contains(ticketToRemove)) {
            ticketList.remove(ticketToRemove);
        }
    }
}
