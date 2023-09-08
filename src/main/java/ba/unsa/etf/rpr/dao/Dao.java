package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exception.JukeBoxException;

import java.util.List;

public interface Dao<T> {

    T getById(int id) throws JukeBoxException;

    T add(T item) throws JukeBoxException;

    T update(T item) throws JukeBoxException;

    void delete(int id) throws JukeBoxException;

    List<T> getAll() throws JukeBoxException;
}
