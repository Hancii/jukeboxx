package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Song;
import ba.unsa.etf.rpr.exception.JukeBoxException;

import java.util.List;

public class SongManager {

    public void validateSong(Song song) throws JukeBoxException {
        if (song == null || song.getName() == null || song.getName().isEmpty()) {
            throw new JukeBoxException("Song not valid");
        }
    }

    public List<Song> getAll() throws JukeBoxException {
        return DaoFactory.songDao().getAll();
    }

    public Song add(Song song) throws JukeBoxException {
        validateSong(song);

        return DaoFactory.songDao().add(song);
    }
    /**
     * Method which allows us to update a song
     * in the Song table
     * @param song we want to update
     * @throws JukeBoxException
     */
    public Song update(Song song) throws JukeBoxException {
        validateSong(song);

        return DaoFactory.songDao().update(song);
    }
    /**
     * Method which allows us to delete a song from
     * the database that has the provided id
     * @param id of the element we want to delete
     * @throws JukeBoxException
     */
    public void delete(int id) throws JukeBoxException {
        DaoFactory.singerDao().delete(id);
    }

}
