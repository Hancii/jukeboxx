package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.exception.JukeBoxException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class CategoryDaoSQLImpl extends AbstractDao<Category> implements CategoryDao {

    public CategoryDaoSQLImpl() {
        super("category");
    }

    @Override
    public Category row2object(ResultSet rs) throws JukeBoxException {
        try {
            Category category = new Category();
            category.setId(rs.getInt(1)); // "id"
            category.setName(rs.getString(2));
            return category;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new JukeBoxException("Cannot convert Category row to object.", e);
        }
    }

    @Override
    public Map<String, Object> object2row(Category category) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", category.getId());
        row.put("name", category.getName());
        return row;
    }
}
