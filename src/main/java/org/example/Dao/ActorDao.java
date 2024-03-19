package org.example.Dao;

import org.example.Models.Actor;
import org.example.Models.Director;
import org.example.Models.Film;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ActorDao {
    private final SessionFactory factory;
    public ActorDao() {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Director.class)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(Actor.class);
        factory = configuration.buildSessionFactory();
    }
    public int createOrUpdate(Actor actor){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(actor);
            session.getTransaction().commit();
            return actor.getId();
        }
    }
    public Actor getActor(int id)
    {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Actor actor = session.get(Actor.class, id);
            session.getTransaction().commit();
            return actor;
        }
    }
    public void delete(int id)
    {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Actor actor = session.get(Actor.class, id);
            session.delete(actor);
            session.getTransaction().commit();
        }
    }
    public List<Actor> getActors() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Actor> actors =  session.createQuery("from Actor", Actor.class)
                    .list();
            session.getTransaction().commit();
            return actors;
        }
    }
    public List<Actor> getActorsWithNFilms(int n) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Actor> actors =  session.createQuery("from Actor", Actor.class)
                    .list();

            session.getTransaction().commit();
            actors = actors.stream().filter(x -> x.getFilms().size() >= n).toList();
            return actors;
        }
    }
    public List<Actor> getActorsAndDirectors() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session
                    .createNativeQuery("SELECT actor_id, actors.full_name, actors.date_of_birth FROM actors INNER JOIN directors " +
                                    "ON actors.full_name = directors.full_name", Actor.class);
            List<Actor> actors =  query.getResultList();
            session.getTransaction().commit();
            return actors;
        }
    }
}
