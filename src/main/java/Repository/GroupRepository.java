package Repository;

import Model.Booking;
import Model.Group;
import Model.Movie;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class GroupRepository {
    private SessionFactory sessionFactory;

    public GroupRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Group> getGroups(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Group> groupList = null;
        try {
            groupList = session.createQuery("FROM MGroup", Group.class).list();
        } catch (NoResultException ignored) {

        }
        session.getTransaction().commit();
        session.close();
        return groupList;
    }

    public void addGroup(Group newGroup) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(newGroup);
        session.getTransaction().commit();
        session.close();
    }

    public void findGroupByName(String groupName) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Group group = null;
        try {
            group = session.createQuery("From MGroup WHERE name = :grName ", Group.class)
                    .setParameter("grName", groupName).getSingleResult();
        } catch (Exception ignored) {

        }
        session.getTransaction().commit();
        session.close();
    }

    public void deleteGroup(Group groupToDelete){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(groupToDelete);
        session.getTransaction().commit();
        session.close();
    }

    public void updateMovie(Group groupToUpdate){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Group group = session.load(Group.class,groupToUpdate.getId());
        group.setGroupDescription(group.getGroupDescription());
        group.setName(group.getName());
        group.setHowManyMembers(group.getHowManyMembers());
        session.update(group);
        session.getTransaction().commit();
        session.close();
    }
}
