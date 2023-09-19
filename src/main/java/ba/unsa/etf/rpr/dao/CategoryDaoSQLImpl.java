package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.exception.JukeBoxException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class CategoryDaoSQLImpl extends AbstractDao<Category> implements CategoryDao {

    private static CategoryDaoSQLImpl instance = null;

    private CategoryDaoSQLImpl() {
        super("category");
    }

    public static CategoryDao getInstance() {
        if (instance == null) {
            instance = new CategoryDaoSQLImpl();
        }
        return instance;
    }

    /**
     * Converts results set into Category object by reading appropriate columns
     *
     * @param  rs the result set to convert
     * @return      category that is converted from result set
     * */
    @Override
    public Category row2object(ResultSet rs) throws JukeBoxException {
        try {
            Category category = new Category();
            category.setId(rs.getInt("id")); // "id"
            category.setName(rs.getString("name"));
            return category;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new JukeBoxException("Cannot convert Category row to object.", e);
        }
    }



}
