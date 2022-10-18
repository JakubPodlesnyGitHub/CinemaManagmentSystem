package Repository;

import Model.CinemaScrenning;
import org.hibernate.SessionFactory;

public class RepositoryManager {
    private SessionFactory sessionFactory;
    private ProgramUserRepository programUserRepository;
    private SysRoleRepository sysRoleRepository;
    private MovieRepository movieRepository;
    private BookingRepository bookingRepository;
    private CinemaScrenningRepository cinemaScrenningRepository;

    public RepositoryManager(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        programUserRepository = new ProgramUserRepository(sessionFactory);
        cinemaScrenningRepository = new CinemaScrenningRepository(sessionFactory);
        sysRoleRepository = new SysRoleRepository(sessionFactory);
        bookingRepository = new BookingRepository(sessionFactory);
        movieRepository = new MovieRepository(sessionFactory);
    }

    public ProgramUserRepository getProgramUserRepository() {
        return programUserRepository;
    }

    public SysRoleRepository getSysRoleRepository() {
        return sysRoleRepository;
    }

    public MovieRepository getMovieRepository() {
        return movieRepository;
    }

    public BookingRepository getBookingRepository() {
        return bookingRepository;
    }

    public CinemaScrenningRepository getCinemaScrenningRepository() {
        return cinemaScrenningRepository;
    }
}
