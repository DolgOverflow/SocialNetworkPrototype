package testgroup.filmography.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import testgroup.filmography.model.Film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class FilmDaoImp implements FilmDao {


    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    private static Map<Integer, Film> films = new HashMap<>();

    ////TestFilm
//    static{
//        Film film1 = new Film();
//        film1.setId(AUTO_ID.getAndIncrement());
//        film1.setTitle("Contraception");
//        film1.setYear(2000);
//        film1.setGenre("Drama");
//        film1.setWatched(true);
//        films.put(film1.getId(),film1);
//
//        Film film2= new Film();
//    }
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override

    public List<Film> allFilms() {
//
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Film").list();
//List<Film> list= new ArrayList<>(films.values());
//        return list;
    }

    @Override
    public void add(Film film) {
Session session = sessionFactory.getCurrentSession();
session.persist(film);
//        film.setId(AUTO_ID.getAndIncrement());
//        films.put(film.getId(), film);
//
}

    @Override
    public void delete(Film film) {
        Session session= sessionFactory.getCurrentSession();
      session.delete(film);
        //  films.remove(film.getId());
    }

    @Override
    public void edit(Film film) {
        Session session = sessionFactory.getCurrentSession();
        session.update(film);
        //films.put(film.getId(), film);
    }
@Override
    public Film getById(int id) {
Session session = sessionFactory.getCurrentSession();
        return session.get(Film.class, id);
    }

}
