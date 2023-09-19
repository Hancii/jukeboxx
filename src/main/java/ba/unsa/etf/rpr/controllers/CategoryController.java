package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.CategoryManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.exception.JukeBoxException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class CategoryController implements Initializable {

    private CategoryManager categoryManager = new CategoryManager();
    private Category selectedCategory;

    public ListView<Category> categoryList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            refresh();
        } catch (JukeBoxException e) {
            throw new RuntimeException(e);
        }

        categoryList.getSelectionModel().selectedItemProperty().addListener(((observableValue, category, t1) -> {
            System.out.println("Selected: " + t1);
            selectedCategory = t1;
        }));
    }

    public void addCategory(ActionEvent actionEvent) {
        System.out.println("Adding category");
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/add_category.fxml"));
            AddCategoryController controller = new AddCategoryController();
            fxmlLoader.setController(controller);
            Parent root = fxmlLoader.load();
            stage.setTitle("Juke Box v1.0");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.setOnHiding(windowEvent -> {
                try {
                    refresh();
                } catch (JukeBoxException e) {
                    throw new RuntimeException(e);
                }
            });
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void next() {
        if (selectedCategory == null) {
            return;
        }
        System.out.println("Opening category: " + selectedCategory);
        try {
            Stage stage = (Stage) categoryList.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/singers.fxml"));
            SingerController controller = new SingerController(selectedCategory);
            fxmlLoader.setController(controller);
            Parent root = fxmlLoader.load();
            stage.setTitle("Juke Box v1.0");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void refresh() throws JukeBoxException {
        categoryList.setItems(FXCollections.observableArrayList(categoryManager.getAll()));
    }
}
