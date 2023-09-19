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


}
