package Repository;

import Model.ProgramUser;
import Model.SysRole;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ProgramUserRepository {
    private SessionFactory sessionFactory;

    public ProgramUserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void activateAccount(ProgramUser programUser) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ProgramUser programUserUpdated = session.load(ProgramUser.class, programUser.getIdUser());
        programUserUpdated.setAccountActive(true);
        session.update(programUserUpdated);
        session.getTransaction().commit();
        session.close();
    }

    public void deactivateAccount(ProgramUser programUser) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ProgramUser programUserUpdated = session.load(ProgramUser.class, programUser.getIdUser());
        programUserUpdated.setAccountActive(false);
        session.update(programUserUpdated);
        session.getTransaction().commit();
        session.close();
    }

    public void addNewProgramUser(ProgramUser newProgramUser) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(newProgramUser);
        session.getTransaction().commit();
        session.close();
    }

    public void modifyProgramUser(ProgramUser programUserToUpdate) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ProgramUser programUser = session.load(ProgramUser.class, programUserToUpdate.getIdUser());
        programUser.setFirstName(programUserToUpdate.getFirstName());
        programUser.setLastName(programUserToUpdate.getLastName());
        programUser.setBirthDate(programUserToUpdate.getBirthDate());
        programUser.setAddress(programUserToUpdate.getAddress());
        session.update(programUser);
        session.getTransaction().commit();
        session.close();
    }

    public void changePassword(String newPassword, ProgramUser userToUpdate) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ProgramUser programUser = session.load(ProgramUser.class, userToUpdate.getIdUser());
        programUser.setPassword(newPassword);
        session.update(programUser);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteProgramUserAccount(ProgramUser programUserToDelete) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(programUserToDelete);
        session.getTransaction().commit();
        session.close();
    }

    public void changeUserRole(ProgramUser programUser, SysRole sysRole) {
        programUser.setSysRole(sysRole);
    }

    public ProgramUser checkIfUserExists(String login, String password) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ProgramUser user = null;
        try {
            user = session.createQuery("FROM ProgramUser WHERE login = :login AND password = :password", ProgramUser.class)
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException ignored) {

        }
        session.getTransaction().commit();
        session.close();
        return user;
    }

    public List<ProgramUser> getAllProgramUsers() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ProgramUser> programUsers = null;
        try {
            programUsers = session.createQuery("FROM ProgramUser ", ProgramUser.class).list();
        } catch (NoResultException ignored) {

        }
        session.getTransaction().commit();
        session.close();
        return programUsers;
    }

    public void setNewUserCode(ProgramUser personToUpdate, String newActivationCode) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ProgramUser programUser = session.load(ProgramUser.class, personToUpdate.getIdUser());
        programUser.setAccessCode(newActivationCode);
        session.update(programUser);
        session.getTransaction().commit();
        session.close();
        personToUpdate.setAccessCode(newActivationCode);
    }

    public ProgramUser getProgramUserByCode(String code) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ProgramUser user = null;
        try {
            user = session.createQuery("FROM ProgramUser WHERE accessCode = :code", ProgramUser.class)
                    .setParameter("code", code)
                    .getSingleResult();
        } catch (NoResultException ignored) {

        }
        session.getTransaction().commit();
        session.close();
        return user;
    }

}
