package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import static jm.task.core.jdbc.util.Util.getSessionFactory;


public class UserDaoHibernateImpl implements UserDao {


    public final static String CREATE = "CREATE TABLE IF NOT EXISTS user " +
            "(id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL, name VARCHAR(45), lastname VARCHAR(45), age TINYINT);";

    public final static String DROP = "DROP TABLE IF EXISTS user;";
    public final static String CLEAR = "DELETE FROM user;";

    public final static String REMOVE = "DELETE FROM user WHERE id= :id";
    public final static String GETALL = "SELECT * FROM user;";


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = getSessionFactory().openSession();
        try (session) {
            Transaction transaction = session.beginTransaction();


            Query query = session.createSQLQuery(CREATE).addEntity(User.class);
            query.executeUpdate();

            transaction.commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();


        }

    }

    @Override
    public void dropUsersTable() {
        Session session = getSessionFactory().openSession();
        try (session) {
            Transaction transaction = session.beginTransaction();


            Query query = session.createSQLQuery(DROP).addEntity(User.class);
            query.executeUpdate();

            transaction.commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();


        }

    }


    @Override
    public void saveUser(User user) {

        Session session = getSessionFactory().openSession();
        try (session) {
            Transaction transaction = session.beginTransaction();

            session.save(user);


            transaction.commit();
            session.close();

            System.out.println("User с именем – " + user.getName() + " добавлен в базу данных");

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();


        }


    }


    @Override
    public void removeUserById(long id) {
        Session session = getSessionFactory().openSession();
        try (session) {
            Transaction transaction = session.beginTransaction();


            Query query = session.createSQLQuery(REMOVE).addEntity(User.class);
            query.setParameter("id", id);
            query.executeUpdate();

            transaction.commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();


        }


    }

    @Override
    public List<User> getAllUsers() {

        Session session = getSessionFactory().openSession();
        List<User> userList = null;
        try (session) {
            Transaction transaction = session.beginTransaction();


            Query query = session.createSQLQuery(GETALL).addEntity(User.class);
            userList = query.list();


            transaction.commit();
            session.close();

            System.out.println(userList);

        } catch (Exception e) {
            e.printStackTrace();


        }

        return userList;

    }

    @Override
    public void cleanUsersTable() {
        Session session = getSessionFactory().openSession();
        try (session) {
            Transaction transaction = session.beginTransaction();


            Query query = session.createSQLQuery(CLEAR).addEntity(User.class);
            query.executeUpdate();

            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();


        }
    }

}
