package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exception.JukeBoxException;

import java.sql.*;
import java.util.*;

public abstract class AbstractDao<T extends Idable> implements Dao<T> {
    private static Connection connection = null;
    protected final String tableName;

    public AbstractDao(String tableName) {
        this.tableName = tableName;
        createConnection();
    }

    private static void createConnection() {
        if (AbstractDao.connection == null) {
            try {
                Properties p = new Properties();
                p.load(ClassLoader.getSystemResource("application.properties").openStream());
                String url = p.getProperty("db.connection_string");
                String username = p.getProperty("db.username");
                String password = p.getProperty("db.password");
                AbstractDao.connection = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Runtime.getRuntime().addShutdownHook(new Thread() {
                    @Override
                    public void run() {
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    public static Connection getConnection() {
        return AbstractDao.connection;
    }

    public abstract T row2object(ResultSet rs) throws JukeBoxException;


    public abstract Map<String, Object> object2row(T object) throws JukeBoxException;

    public T getById(int id) throws JukeBoxException {
        return executeQueryUnique("SELECT * FROM " + this.tableName + " WHERE id = ?", new Object[]{id});
    }

    public List<T> getAll() throws JukeBoxException {
        return executeQuery("SELECT * FROM " + tableName, null);
    }

    public void delete(int id) throws JukeBoxException {
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new JukeBoxException(e.getMessage(), e);
        }
    }


}

