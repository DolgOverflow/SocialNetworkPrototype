package testgroup.filmography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testgroup.filmography.dao.FilmDao;
import testgroup.filmography.dao.FilmDaoImp;
import testgroup.filmography.model.Film;

import java.util.List;

@Service
public class FilmServiceImp implements FilmService {

    FilmDaoImp filmDao;

    @Autowired
    private void setFilmDao(FilmDaoImp filmDao) {
        this.filmDao = filmDao;
    }

    @Override
    @Transactional
    public List<Film> allFilms() {
        return filmDao.allFilms();
    }

    @Override
    @Transactional
    public void add(Film film) {
        filmDao.add(film);
    }

    @Override
    @Transactional
    public void delete(Film film) {
        filmDao.delete(film);
    }

    @Override
    @Transactional
    public void edit(Film film) {
        filmDao.edit(film);
    }

    @Override
    @Transactional
    public Film getById(int id) {
        return filmDao.getById(id);
    }
}
