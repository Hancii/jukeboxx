package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.CategoryManager;
import ba.unsa.etf.rpr.dao.CategoryDao;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.exception.JukeBoxException;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCategoryController implements Initializable {

    private CategoryManager categoryManager = new CategoryManager();

    public TextField categoryNameInput;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void addCategory(ActionEvent actionEvent) throws JukeBoxException {
        Category category = new Category();
        category.setName(categoryNameInput.getText());
        categoryManager.add(category);

        Stage stage = (Stage) categoryNameInput.getScene().getWindow();
        stage.close();
    }
}
