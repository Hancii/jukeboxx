package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.CategoryDao;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.exception.JukeBoxException;

import java.util.List;

public class CategoryManager {

    private final CategoryDao categoryDao = DaoFactory.categoryDao();

    public CategoryManager() {}

    public void validateCategoryName(String name) throws JukeBoxException {
        if (name == null || name.length() > 45 || name.length() < 3) {
            throw new JukeBoxException("Category name must be between 3 and 45 chars");
        }
    }

    public List<Category> getAll() throws JukeBoxException {
        return categoryDao.getAll();
    }



}
