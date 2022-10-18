package Repository;

import Model.Booking;
import Model.Movie;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.awt.print.Book;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class BookingRepository {
    private SessionFactory sessionFactory;

    public BookingRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addNewBooking(Booking newBooking) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(newBooking);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteBooking(Booking bookingToDelete) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(bookingToDelete);
        session.getTransaction().commit();
        session.close();
    }

    public List<Booking> getBookings() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Booking> bookingList = null;
        try {
            bookingList = session.createQuery("FROM Booking", Booking.class).list();
        } catch (NoResultException ignored) {

        }
        session.getTransaction().commit();
        session.close();
        return bookingList;
    }

    public void changeBookingDate(LocalDate newDate, Booking bookingToChange) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Booking booking = session.load(Booking.class, bookingToChange.getIdBooking());
        booking.setCreationDate(Date.valueOf(newDate));
        session.update(booking);
        session.getTransaction().commit();
        session.close();
    }

    public void changeBookingInfo(Booking bookingToChange) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Booking booking = session.load(Booking.class, bookingToChange.getIdBooking());
        booking.setNumberOfSeats(bookingToChange.getNumberOfSeats());
        session.update(booking);
        session.getTransaction().commit();
        session.close();
    }
}
