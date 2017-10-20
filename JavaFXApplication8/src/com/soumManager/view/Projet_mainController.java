package com.soumManager.view;

import com.soumManager.model.ListePrix;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Projet_mainController implements Initializable {

    @FXML
    private TableView<ListePrix> tableView;
    @FXML
    private TableColumn<ListePrix, String> titre;
    
    private RootLayoutController rootLayout;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titre.setCellValueFactory(cellData -> cellData.getValue().titreProperty());
    }    

    void setRootLayout(RootLayoutController aThis) {
        this.rootLayout = aThis;
        tableView.setItems(rootLayout.getProjet().getListesPrix());        
    }

}
