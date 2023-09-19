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
    /**
     * Method that lets us get all the singers from the database
     * @return
     * @throws JukeBoxException
     */
    public List<Singer> getAll() throws JukeBoxException {
        return DaoFactory.singerDao().getAll();
    }

//    public List<Singer> searchByCategory(Category category) throws JukeBoxException {
//        return DaoFactory.singerDao().searchByCategory(category);
//    }
    /**
     * Method that lets us add singers to database
     * @return
     * @throws JukeBoxException
     */
    public Singer add(Singer singer) throws JukeBoxException {
        validateSinger(singer);

        return DaoFactory.singerDao().add(singer);
    }
    /**
     * Method which allows us to update singer
     * in the Singer table
     * @param singer we want to update
     * @throws JukeBoxException
     */
    public Singer update(Singer singer) throws JukeBoxException {
        validateSinger(singer);

        return DaoFactory.singerDao().update(singer);
    }

    public void delete(int id) throws JukeBoxException {
        DaoFactory.singerDao().delete(id);
    }

}
