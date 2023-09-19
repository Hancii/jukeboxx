package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Singer;
import ba.unsa.etf.rpr.exception.JukeBoxException;

import java.util.List;

public class SingerManager {

    public void validateSinger(Singer singer) throws JukeBoxException {
        if (singer == null || singer.getName() == null || singer.getName().isEmpty()) {
            throw new JukeBoxException("Singer not valid");
        }
    }



}
