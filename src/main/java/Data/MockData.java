package Data;

import Model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MockData {
    private SessionFactory sessionFactory;
    private ProgramUser programUser1, programUser2, programUser3;
    private SysRole managerRole, clientRole, cashierRole;
    private Movie movie1, movie2, movie3;
    private CinemaScrenning cinemaScreening1, cinemaScreening2, cinemaScreening3;
    private Booking booking1, booking2, booking3;
    private Ticket ticket1, ticket2, ticket3, ticket4;

    public MockData(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        createUsers();
        createRoles();
        createMovies();
        createBookings();
        createTickets();
        createCinemaScreenings();
        persistRolesUsersToDatabase();
        persistBookingTickets();
        persistMoviesToDatabase();
        persistCinemaScreeningsToDatabase();
    }


    public void createMovies() {
        movie1 = new Movie("Titanic", "Dramat", "osoba 1", LocalDate.of(2001, 5, 25), "Movie is about Titanic");
        movie2 = new Movie("Iron Man", "SF", "osoba 2", LocalDate.of(2008, 6, 10), "Movie is about Iron Man");
        movie3 = new Movie("Lord Of The Rings", "Fantasy", "osoba 3", LocalDate.of(2001, 5, 15), "Movie is about LOTR");
    }

    private void createCinemaScreenings() {
        cinemaScreening1 = new CinemaScrenning(LocalDateTime.of(2001, 6, 24, 15, 12));
        cinemaScreening2 = new CinemaScrenning(LocalDateTime.of(2009, 7, 10, 12, 12));
        cinemaScreening3 = new CinemaScrenning(LocalDateTime.of(2002, 12, 21, 10, 11));
        cinemaScreening1.setMovie(movie1);
        cinemaScreening2.setMovie(movie2);
        cinemaScreening3.setMovie(movie3);
    }

    private void createUsers() {
        programUser1 = new ProgramUser("Jakub", "Podlesny", LocalDate.of(2000, 4, 13), new Address("Akacjowa", 4, 24, "Mazowieckie", "02-495", "Warszawa", "Polska"), "example12345r@gmail.com", "jakub111", "jakub111", null);
        programUser2 = new ProgramUser("Maciej", "Podlesny", LocalDate.of(2000, 6, 25), new Address("Jacka i Agatki", 5, 23, "Mazowieckie", "02-495", "Warszawa", "Polska"), "example12345r@gmail.com", "maciej111", "maciej111", null);
        programUser3 = new ProgramUser("Michal", "Nowak", LocalDate.of(2000, 9, 5), new Address("Złota", 6, 25, "Mazowieckie", "02-495", "Warszawa", "Polska"), "example12345r@gmail.com", "michal111", "michal111", null);
    }

    private void createRoles() {
        clientRole = new SysRole("Client", "Client User Role");
        managerRole = new SysRole("Manager", "Manager User Role");
        cashierRole = new SysRole("Cashier", "Cashier User Role");
        programUser1.setSysRole(clientRole);
        programUser2.setSysRole(cashierRole);
        programUser3.setSysRole(managerRole);
    }

    private void createBookings() {
        booking1 = new Booking(1);
        booking2 = new Booking(2);
        booking3 = new Booking(1);
    }

    public void createTickets() {
        ticket1 = new Ticket(1, "Ticket 1");
        ticket2 = new Ticket(2, "Ticket 2");
        ticket3 = new Ticket(3, "Ticket 3");
        ticket4 = new Ticket(4, "Ticket 4");
        ticket1.setBooking(booking1);
        ticket2.setBooking(booking2);
        ticket3.setBooking(booking2);
        ticket3.setBooking(booking3);
    }

    public void persistBookingTickets(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(booking1);
        session.persist(booking2);
        session.persist(booking3);
        session.persist(ticket1);
        session.persist(ticket2);
        session.persist(ticket3);
        session.persist(ticket4);
        session.getTransaction().commit();
        session.close();
    }

    public void persistMoviesToDatabase() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(movie1);
        session.persist(movie2);
        session.persist(movie3);
        session.getTransaction().commit();
        session.close();
    }

    public void persistCinemaScreeningsToDatabase() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(cinemaScreening1);
        session.persist(cinemaScreening2);
        session.persist(cinemaScreening3);
        session.getTransaction().commit();
        session.close();
    }

    private void persistRolesUsersToDatabase() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(programUser1);
        session.persist(programUser2);
        session.persist(programUser3);
        session.persist(cashierRole);
        session.persist(clientRole);
        session.persist(managerRole);
        session.getTransaction().commit();
        session.close();
    }


}
