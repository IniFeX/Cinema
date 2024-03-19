package org.example.Dao;

import org.example.Models.Actor;
import org.example.Models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class UserDao {
    private final SessionFactory factory;
    public UserDao() {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(User.class);
        factory = configuration.buildSessionFactory();
    }
    public int create(User user){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
            return user.getId();
        }
    }
    public User getUser(int id)
    {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.getTransaction().commit();
            return user;
        }
    }
    public User getUserByUsername(String username)
    {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from User where username = :username", User.class);
            query.setParameter("username", username);
            User user = (User) query.getSingleResult();
            session.getTransaction().commit();
            return user;
        }
    }
    public void delete(int id)
    {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.detach(user);
            session.getTransaction().commit();
        }
    }
}
