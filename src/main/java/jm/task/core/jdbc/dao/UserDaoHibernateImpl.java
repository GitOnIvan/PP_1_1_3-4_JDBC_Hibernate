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
    public final static String ADD = "INSERT INTO user (name, lastname, age) VALUES (:name, :lastname, :age);";
    public final static String REMOVE = "DELETE FROM user WHERE id= :id";
    public final static String GETALL = "SELECT * FROM user;";


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();


        Query query = session.createSQLQuery(CREATE).addEntity(User.class);
        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();


        Query query = session.createSQLQuery(DROP).addEntity(User.class);
        query.executeUpdate();

        transaction.commit();
        session.close();

    }


    @Override
    public void saveUser(String name, String lastName, byte age) {

        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();



        Query query = session.createSQLQuery(ADD).addEntity(User.class);
        query.setParameter("name", name);
        query.setParameter("lastname", lastName);
        query.setParameter("age", age);
        query.executeUpdate();

        transaction.commit();
        session.close();

        System.out.println("User с именем – " + name +" добавлен в базу данных");
    }





    @Override
    public void removeUserById(long id) {

        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();



        Query query = session.createSQLQuery(REMOVE).addEntity(User.class);
        query.setParameter("id", id);
        query.executeUpdate();

        transaction.commit();
        session.close();

    }

    @Override
    public List<User> getAllUsers() {

        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();



        Query query = session.createSQLQuery(GETALL).addEntity(User.class);
        List<User> userList = query.list();


        transaction.commit();
        session.close();

        System.out.println(userList);

        return userList;

    }

    @Override
    public void cleanUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();



        Query query = session.createSQLQuery(CLEAR).addEntity(User.class);
        query.executeUpdate();

        transaction.commit();
        session.close();


    }
}
