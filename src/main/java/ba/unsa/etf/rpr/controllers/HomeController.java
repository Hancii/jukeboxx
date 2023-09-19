package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class HomeController implements Initializable {

    public Label title;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void startJukeBox() {
        try {
            Stage stage = (Stage) title.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/categories.fxml"));
            CategoryController controller = new CategoryController();
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
}
