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
    /**
     * Converts results set into songs object by reading appropriate columns
     *
     * @param  rs the result set to convert
     * @return      song that is converted from result set
     * */
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
    /**
     * Converts results set into Song object by reading appropriate columns
     *
     * @param  song the song to convert to Map
     * @return      map that is converted from Song object
     * */
    @Override
    public Map<String, Object> object2row(Song song) throws JukeBoxException {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", song.getId());
        row.put("name", song.getName());
        row.put("link", song.getLink());
        row.put("singer_id", song.getSinger().getId());
        return row;
    }

    @Override
    public List<Song> getByNameLink(String name, String link) throws JukeBoxException {
        return null;
    }
}
