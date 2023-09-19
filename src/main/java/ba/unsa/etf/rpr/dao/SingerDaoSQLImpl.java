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


}
