package com.soumManager.view;

import com.soumManager.model.Catalog;
import com.soumManager.model.Position21;
import com.soumManager.model.Sia451;
import com.soumManager.data.Sia451_XML;
import com.soumManager.utils.Config;
import com.soumManager.utils.Log;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Sia451Controller implements Initializable {
    //POSITION
    @FXML
    private TableView<Position21> tablePos;    
    @FXML
    private TableColumn<Position21, String> numPos;
    @FXML
    private TableColumn<Position21, String> textPos;
    @FXML
    private TableColumn<Position21, String> quantite;
    @FXML
    private TableColumn<Position21, String> unit;
    @FXML
    private TableColumn<Position21, String> prix;
    @FXML
    private TableColumn<Position21, String> total;    

    //CATALOG
    @FXML
    private TableView<Catalog> tableCat;
    @FXML
    private TableColumn<Catalog, String> numCat;
    @FXML
    private TableColumn<Catalog, String> textCat;
    @FXML
    private TableColumn<Catalog, String> prixCat;
    
    @FXML
    private ComboBox<Catalog> cb;
        
    private int numCatalogSelect=0;
    private Sia451 sia451;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*sia451 = new Sia451(Config.getFileSia451());
        
        cb.setItems(sia451.getProjet().getListCatChapter());
        cb.getSelectionModel().selectFirst();
        cb.valueProperty().addListener((observable, oldValue, newValue) -> {updateValue((int) newValue.getNumCatalog());});
        
        numPos.setCellValueFactory(cellData -> cellData.getValue().numPosProperty());
        textPos.setCellValueFactory(cellData -> cellData.getValue().textProperty());
        quantite.setCellValueFactory(cellData -> cellData.getValue().quantiteProperty());
        unit.setCellValueFactory(cellData -> cellData.getValue().unitProperty());
        prix.setCellValueFactory(cellData -> cellData.getValue().prixProperty());
        total.setCellValueFactory(cellData -> cellData.getValue().totalProperty());
        
        numCat.setCellValueFactory(cellData -> cellData.getValue().numCatalogProperty());
        textCat.setCellValueFactory(cellData -> cellData.getValue().nomCatProperty());
        prixCat.setCellValueFactory(cellData -> cellData.getValue().prixCatalogProperty());
        
        numCatalogSelect = cb.getSelectionModel().getSelectedItem().getNumCatalog();

        tablePos.setItems(sia451.getProjet().getListPosition().filtered(p -> p.getNumCatalog() == this.numCatalogSelect));
        tablePos.setVisible(false);

        tableCat.setItems(sia451.getProjet().getListCatChapterToRecap());
        tableCat.setVisible(true);*/
    }
    private void updateValue(int num){
       /* numCatalogSelect = cb.getSelectionModel().getSelectedItem().getNumCatalog();
        tablePos.setItems(sia451.getProjet().getListPosition().filtered(p -> p.getNumCatalog() == this.numCatalogSelect));
        if(numCatalogSelect == 0)
        {
            tablePos.setVisible(false);
            tableCat.setVisible(true);
        }
        else
        {
            tablePos.setVisible(true);
            tableCat.setVisible(false);            
        }*/
    }    
}
