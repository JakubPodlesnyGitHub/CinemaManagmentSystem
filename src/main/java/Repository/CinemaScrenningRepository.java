package Repository;

import Model.CinemaScrenning;
import Model.Movie;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CinemaScrenningRepository {
    SessionFactory sessionFactory;

    public CinemaScrenningRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Movie> getMoviesByDateHours(LocalDate date, int hour, int minutes) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<CinemaScrenning> cinemaScrennings = null;
        List<Movie> movies = new ArrayList<>();
        try {
            cinemaScrennings = session.createQuery("FROM CinemaScrenning WHERE screnningDateHour = :movieDateHour", CinemaScrenning.class)
                    .setParameter("movieDateHour", LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), hour, minutes)).list();
        } catch (NoResultException ignored) {

        }
        session.getTransaction().commit();
        session.close();
        assert cinemaScrennings != null;
        for (CinemaScrenning cinemaScrenning : cinemaScrennings) {
            movies.add(cinemaScrenning.getMovie());
        }
        return movies;
    }
}
