package org.example.Dao;

import org.example.Models.Actor;
import org.example.Models.Director;
import org.example.Models.Film;
import org.example.Models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class FilmDao {
    private final SessionFactory factory;
    public FilmDao() {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(Director.class)
                .addAnnotatedClass(Actor.class);
        factory = configuration.buildSessionFactory();
    }
    public int create(Film film){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.persist(film);
            session.getTransaction().commit();
            return film.getId();
        }
    }
    public Film getFilm(int id)
    {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Film film = session.get(Film.class, id);
            film.getDirector();
            film.getActors();
            System.out.println(film.getActors());
            session.getTransaction().commit();
            return film;
        }
    }
    public void delete(int id)
    {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Film film = session.get(Film.class, id);
            session.delete(film);
            session.getTransaction().commit();
        }
    }

    public List<Film> getFilms() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Film> films =  session.createQuery("from Film", Film.class)
                    .list();
            session.getTransaction().commit();
            return films;
        }
    }
    public List<Film> thisAndLastYearFilms() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session
                    .createNativeQuery("SELECT * FROM films WHERE :year = EXTRACT(year FROM issued_at) OR " +
                                    ":year - 1 = EXTRACT(year FROM issued_at)",
                    Film.class);
            query.setParameter("year", LocalDate.now().getYear());

            List<Film> films =  query.getResultList();
            session.getTransaction().commit();
            return films;
        }
    }
    public void deleteFilmsWithAge(int age) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session
                    .createNativeQuery("DELETE FROM films WHERE :year > EXTRACT(year FROM issued_at)",
                            Film.class);
            query.setParameter("year", LocalDate.now().getYear() - age);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }
}
