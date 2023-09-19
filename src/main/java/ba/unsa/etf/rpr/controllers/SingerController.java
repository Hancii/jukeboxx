package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.SingerManager;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Singer;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class SingerController implements Initializable {

    private Category category;
    private SingerManager singerManager = new SingerManager();

    private Singer selectedSinger;

    public ListView<Singer> singersList;

    public SingerController(Category category) {
        this.category = category;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
