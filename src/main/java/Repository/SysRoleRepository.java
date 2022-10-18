package Repository;

import Model.SysRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SysRoleRepository {
    SessionFactory sessionFactory;

    public SysRoleRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addSysRole(SysRole newRole) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(newRole);
        session.getTransaction().commit();
        session.close();
    }

    public void removeSysRole(SysRole roleToRemove) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(roleToRemove);
        session.getTransaction().commit();
        session.close();
    }
}
