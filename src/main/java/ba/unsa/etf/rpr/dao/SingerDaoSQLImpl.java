package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Singer;
import ba.unsa.etf.rpr.exception.JukeBoxException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SingerDaoSQLImpl extends AbstractDao<Singer> implements SingerDao {
    private static SingerDaoSQLImpl instance = null;

    private SingerDaoSQLImpl() {
        super("singer");
    }

    public static SingerDao getInstance() {
        if (instance == null) {
            instance = new SingerDaoSQLImpl();
        }
        return instance;
    }
    /**
     * Converts results set into Singer object by reading appropriate columns
     *
     * @param  rs the result set to convert
     * @return      category that is converted from result set
     * */
    @Override
    public Singer row2object(ResultSet rs) throws JukeBoxException {
        try {
            Singer singer = new Singer();
            singer.setId(rs.getInt("id"));
            singer.setName(rs.getString("name"));
            singer.setCategory(DaoFactory.categoryDao().getById(rs.getInt("category_id")));
            return singer;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new JukeBoxException("Cannot convert Category row to object.", e);
        }
    }

    @Override
    public Map<String, Object> object2row(Singer singer) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", singer.getId());
        row.put("name", singer.getName());
        row.put("category_id", singer.getCategory().getId());
        return row;
    }

    @Override
    public List<Singer> searchByCategory(Category category) throws JukeBoxException {
//        return executeQuery("SELECT * FROM " + this.tableName + " WHERE category_id = ?", new Object[]{category.getId()});
        return null;
    }
}
