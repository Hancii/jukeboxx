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


}
