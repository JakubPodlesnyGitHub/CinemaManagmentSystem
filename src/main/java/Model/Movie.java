package Model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Movie")
@Access(AccessType.FIELD)
public class Movie {
    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "IdMovie", nullable = false)
    private Long idMovie;
    @Column(name = "MovieName", nullable = false)
    private String movieName;
    @Column(name = "Genere", nullable = false)
    private String genre;
    @Column(name = "MovieDirector", nullable = false)
    private String director;
    @Column(name = "ReleaseDate", nullable = false)
    private Date releaseDate;
    @Column(name = "MovieDescription", nullable = true)
    private String desc;

    @OneToMany(mappedBy = "movie")
    private List<CinemaScrenning> cinemaScrenningList = new ArrayList<>();

    public Movie(String movieName, String genre, String director, LocalDate releaseDate, String desc) {
        setMovieName(movieName);
        setGenre(genre);
        setDirector(director);
        setReleaseDate(Date.valueOf(releaseDate));
        setDesc(desc);
    }

    public Movie() {
    }

    public Long getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Long idMovie) {
        this.idMovie = idMovie;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    //connections
    public void addCinemaScreening(CinemaScrenning newCinemaScreening) {
        if (!cinemaScrenningList.contains(newCinemaScreening)) {
            cinemaScrenningList.add(newCinemaScreening);
            newCinemaScreening.setMovie(this);
        }
    }

    public void removeCinemaScreeningFromCollection(CinemaScrenning cinemaScreeningToRemove) {
        if (cinemaScrenningList.remove(cinemaScreeningToRemove)) {
            cinemaScrenningList.remove(cinemaScreeningToRemove);
        }
    }
}
