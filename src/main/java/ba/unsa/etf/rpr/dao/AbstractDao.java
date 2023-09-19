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

    public T add(T item) throws JukeBoxException {
        Map<String, Object> row = object2row(item);
        Map.Entry<String, String> columns = prepareInsertParts(row);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(tableName);
        builder.append(" (").append(columns.getKey()).append(") ");
        builder.append("VALUES (").append(columns.getValue()).append(")");

        try {
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
            // bind params. IMPORTANT treeMap is used to keep columns sorted so params are bind correctly
            int counter = 1;
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            item.setId(rs.getInt(1)); //set id to return it back */

            return item;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new JukeBoxException(e.getMessage(), e);
        }
    }

    public T update(T item) throws JukeBoxException {
        Map<String, Object> row = object2row(item);
        String updateColumns = prepareUpdateParts(row);
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ")
                .append(tableName)
                .append(" SET ")
                .append(updateColumns)
                .append(" WHERE id = ?");

        try {
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString());
            int counter = 1;
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.setObject(counter, item.getId());
            stmt.executeUpdate();
            return item;
        } catch (SQLException e) {
            throw new JukeBoxException(e.getMessage(), e);
        }
    }

    public int count() throws JukeBoxException {
        StringBuilder builder = new StringBuilder();
        builder.append("COUNT * FROM ")
                .append(tableName);
        try {
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString());
            ResultSet resultSet = stmt.executeQuery();
            return resultSet.getInt(0);
        } catch (SQLException e) {
            throw new JukeBoxException(e.getMessage(), e);
        }
    }

    public List<T> executeQuery(String query, Object[] params) throws JukeBoxException {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            if (params != null) {
                for (int i = 1; i <= params.length; i++) {
                    stmt.setObject(i, params[i - 1]);
                }
            }
            ResultSet rs = stmt.executeQuery();
            ArrayList<T> resultList = new ArrayList<>();
            while (rs.next()) {
                resultList.add(row2object(rs));
            }
            return resultList;
        } catch (SQLException e) {
            throw new JukeBoxException(e.getMessage(), e);
        }
    }

    public T executeQueryUnique(String query, Object[] params) throws JukeBoxException {
        List<T> result = executeQuery(query, params);
        if (result != null && result.size() == 1) {
            return result.get(0);
        } else {
            throw new JukeBoxException("Object not found");
        }
    }


}

