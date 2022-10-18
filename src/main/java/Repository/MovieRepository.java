package Repository;

import Model.Movie;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class MovieRepository {
    SessionFactory sessionFactory;

    public MovieRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addNewMovie(Movie newMovie){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(newMovie);
        session.getTransaction().commit();
        session.close();
    }

    public List<Movie> getMovies() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Movie> movies = null;
        try {
            movies = session.createQuery("FROM Movie", Movie.class).list();
        } catch (NoResultException ignored) {

        }
        session.getTransaction().commit();
        session.close();
        return movies;
    }

    public void deleteMovie(Movie movieToDelete){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(movieToDelete);
        session.getTransaction().commit();
        session.close();
    }

    public void updateMovie(Movie movieToUpdate){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Movie movie = session.load(Movie.class,movieToUpdate.getIdMovie());
        movie.setMovieName(movieToUpdate.getMovieName());
        movie.setGenre(movieToUpdate.getGenre());
        movie.setDirector(movieToUpdate.getDirector());
        movie.setReleaseDate(movieToUpdate.getReleaseDate());
        movie.setDesc(movieToUpdate.getDesc());
        session.update(movie);
        session.getTransaction().commit();
        session.close();
    }
}
