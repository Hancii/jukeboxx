package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Song;
import ba.unsa.etf.rpr.exception.JukeBoxException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SongDaoSQLImpl extends AbstractDao<Song> implements SongDao {

    private static SongDaoSQLImpl instance = null;

    private SongDaoSQLImpl() {
        super("song");
    }

    public static SongDao getInstance() {
        if (instance == null) {
            instance = new SongDaoSQLImpl();
        }
        return instance;
    }

    @Override
    public Song row2object(ResultSet rs) throws JukeBoxException {
        try {
            Song song = new Song();
            song.setId(rs.getInt("id"));
            song.setName(rs.getString("name"));
            song.setLink(rs.getString("link"));
            song.setSinger(DaoFactory.singerDao().getById(rs.getInt("singer_id")));
            return song;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new JukeBoxException("Cannot convert Song row to object.", e);
        }
    }


}
