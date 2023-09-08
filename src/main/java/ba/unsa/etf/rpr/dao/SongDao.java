package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Song;
import ba.unsa.etf.rpr.exception.JukeBoxException;

import java.util.List;

public interface SongDao {

    List<Song> getByNameLink(String name, String link) throws JukeBoxException;
}
