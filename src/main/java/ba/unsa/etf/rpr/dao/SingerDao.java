package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Singer;
import ba.unsa.etf.rpr.exception.JukeBoxException;

import java.util.List;

public interface SingerDao extends Dao<Singer> {

    List<Singer> searchByText(String text) throws JukeBoxException;

    List<Singer> searchByCategory(Category category) throws JukeBoxException;
}