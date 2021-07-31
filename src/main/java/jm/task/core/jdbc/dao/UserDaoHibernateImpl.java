package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = Util.utillHibern().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.createSQLQuery("create table user\n" +
                    "(\n" +
                    "\tid int auto_increment,\n" +
                    "\tname varchar(30) null,\n" +
                    "\tlastName varchar(30) null,\n" +
                    "\tage int null,\n" +
                    "\t\tprimary key (id)\n" +
                    ")").executeUpdate();
            tx.commit();
        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            throw e;
        }finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.utillHibern().openSession();
        session.beginTransaction();
        session.createNativeQuery("drop table if exists user").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Session session = Util.utillHibern().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.utillHibern().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            User removeUser = session.get(User.class, id);
            session.delete(removeUser);
            tx.commit();
        }catch (Exception e){
            if (tx != null) {
                tx.rollback();
            }
        }finally{
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.utillHibern().openSession();
        session.beginTransaction();
        List<User> resultList = new ArrayList<>();
        resultList = session.createQuery("from User").list();
        session.getTransaction().commit();
        session.close();
        return resultList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.utillHibern().openSession();
        session.beginTransaction();
        session.createQuery("DELETE User").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
