package by.bsac.dao;

import by.bsac.models.User;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 */
public class UserDaoIml implements UserDao  {

    @Autowired
    private SessionFactory sf;

    public List<User> findAll() {

        //Get session and transaction objects
        Session s = this.sf.openSession();
        Transaction tr = s.beginTransaction();

        //Create query
        //HQL (Hibernate query language)
        Query q = s.createQuery("from User");
        List<User> users = q.list();

        tr.commit();
        s.close();

        return users;

    }

    public User findByFname(String a_fname) throws NullPointerException {

        Session s = this.sf.openSession();
        Transaction tr = s.beginTransaction();

        User founded_user = null;

        try {
            Query q = s.createQuery("FROM User u WHERE u.fName = :user_fname");
            q.setParameter("user_fname", a_fname);
            founded_user = (User) q.uniqueResult();
            tr.commit();
        }catch (HibernateException exc) {
            exc.printStackTrace();
            tr.rollback();
        }finally {
            s.close();
        }

        return founded_user;

    }

    public List<User> findByLname(String a_lname) {

        Session s = this.sf.openSession();
        Transaction tr = s.beginTransaction();

        Query q = s.createQuery("from User u WHERE u.lName = :user_lname");
        q.setParameter("user_lname", a_lname);
        List<User> users =  q.list();

        return users;
    }

    public User findById(Long a_id) {

        Session s = this.sf.openSession();
        Transaction tr = s.beginTransaction();

        User founded_user = null;

        //Get session and transaction objects
        try {

            founded_user = s.load(User.class, a_id);
            tr.commit();

        }catch (HibernateException exc) {
            System.out.println("User with specified ID (" +a_id +") not found in database.");
            tr.rollback();
            return null;
        }finally {
            s.close();
        }

        return founded_user;
    }

    public void create(User a_user) {

        Session s = this.sf.openSession();
        Transaction tr = s.beginTransaction();

        s.save(a_user);

        tr.commit();
        s.close();

    }

    public void update(User a_user) {

        Session s = this.sf.openSession();
        Transaction tr = s.beginTransaction();

        s.delete(a_user);

        tr.commit();
        s.close();
    }

    public void delete(User a_user) {

    }

}
