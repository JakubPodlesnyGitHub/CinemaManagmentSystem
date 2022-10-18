package Model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ProgramUser")
@Access(AccessType.FIELD)
public class ProgramUser {
    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long idUser;
    @Column(name = "FirstName", nullable = false)
    private String firstName;
    @Column(name = "LastName", nullable = false)
    private String lastName;
    @Column(name = "BirthDate", nullable = false)
    private Date birthDate;

    @Column(name = "Mail", nullable = false)
    private String mail;
    @Column(name = "Login", nullable = false, unique = true)
    private String login;
    @Column(name = "Password", nullable = false)
    private String password;
    @Column(name = "AccessCode", nullable = true, unique = true, length = 25)
    private String accessCode;

    @Column(name = "ActiveAccount", nullable = false)
    private Boolean isAccountActive;
    @JoinColumn(name = "Address", nullable = false)
    private Address address;

    @ManyToOne
    private SysRole sysRole;

    @OneToMany(mappedBy = "owner",fetch = FetchType.EAGER)
    private List<Ticket> ticketList = new ArrayList<>();

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Booking> bookingList = new ArrayList<>();

    public ProgramUser(String firstName, String lastName, LocalDate birthDate, Address address,String mail, String login, String password, String code) {
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(Date.valueOf(birthDate));
        setAddress(address);
        setMail(mail);
        setLogin(login);
        setPassword(password);
        setAccessCode(code);
        setAccountActive(false);
    }

    public ProgramUser() {
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public Boolean getAccountActive() {
        return isAccountActive;
    }

    public void setAccountActive(Boolean accountActive) {
        isAccountActive = accountActive;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    //connections


    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public SysRole getSysRole() {
        return sysRole;
    }


    public void setSysRole(SysRole sysRole) {
        if (sysRole.equals(this.sysRole))
            return;
        if (this.sysRole != null)
            this.sysRole.removeFromUserCollection(this);
        this.sysRole = sysRole;
        sysRole.addToUsersCollection(this);
    }

    public void addToTicketToCollection(Ticket newTicket) {
        if (!ticketList.contains(newTicket)) {
            ticketList.add(newTicket);
            newTicket.setUser(this);
        }
    }

    public void removeTicketFormCollection(Ticket ticketToRemove) {
        if (ticketList.contains(ticketToRemove)) {
            ticketList.remove(ticketToRemove);
        }
    }

    public void addBookingToCollection(Booking newBooking) {
        if (!bookingList.contains(newBooking)) {
            bookingList.contains(newBooking);
            newBooking.setUser(this);
        }
    }

    public void removeBookingFromCollection(Booking newBooking) {
        if (!bookingList.contains(newBooking)) {
            bookingList.contains(newBooking);
            newBooking.setUser(this);
        }
    }
}
