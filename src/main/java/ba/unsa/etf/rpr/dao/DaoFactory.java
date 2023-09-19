package ba.unsa.etf.rpr.dao;

public class DaoFactory {
    private static CategoryDao _categoryDao = CategoryDaoSQLImpl.getInstance();
    private static SingerDao _singerDao = SingerDaoSQLImpl.getInstance();
    private static SongDao _songDao = SongDaoSQLImpl.getInstance();

    public static CategoryDao categoryDao() {
        return _categoryDao;
    }

    public static SingerDao singerDao() {
        return _singerDao;
    }

    public static SongDao songDao() {
        return _songDao;
    }

}
