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



}
