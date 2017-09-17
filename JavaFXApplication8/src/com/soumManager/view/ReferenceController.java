package com.soumManager.view;

import com.soumManager.data.Sql_dbReference;
import com.soumManager.model.Catalog;
import com.soumManager.model.Position;
import com.soumManager.utils.Config;
import com.soumManager.utils.Log;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;

public class ReferenceController implements Initializable {
    @FXML
    private TableView<Position> table;
    
    @FXML
    private TableColumn<Position, String> numPos;
    
    @FXML
    private TableColumn<Position, String> text;
    
    @FXML
    private TableColumn<Position, String> prix;
    
    @FXML
    private TableColumn<Position, String> annee;
    
    @FXML
    private ComboBox<Catalog> cb;
    
    @FXML
    private TextField tf_numPos;
    
    @FXML
    private TextField tf_text;

    @FXML
    private TextField tf_prix;
    
    @FXML
    private TextField tf_annee; 
    
    @FXML
    private Button btn_add;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table.setEditable(true);
        
        cb.setItems(Config.getListCatalog());
        cb.getSelectionModel().selectFirst();
        cb.valueProperty().addListener((observable, oldValue, newValue) -> {updateValue((int) newValue.getNumCatalog());});
        
        btn_add.setOnAction((event) -> {
            Sql_dbReference.addReference(tf_numPos.getText(), tf_text.getText(), tf_prix.getText(), tf_annee.getText(), cb.getSelectionModel().getSelectedItem().getNumCatalog());
            table.setItems(Sql_dbReference.getReferenceByNumCat(cb.getSelectionModel().getSelectedItem().getNumCatalog()));
            tf_numPos.clear();
            tf_text.clear();
            tf_prix.clear();
            tf_annee.clear();
        });
        
        numPos.setCellValueFactory(cellData -> cellData.getValue().numPosProperty());
        numPos.setCellFactory(TextFieldTableCell.forTableColumn());
        numPos.setOnEditCommit((event) ->{
            Sql_dbReference.updateReferenceNumPos(event.getNewValue(), event.getRowValue().getId());
            table.setItems(Sql_dbReference.getReferenceByNumCat(cb.getSelectionModel().getSelectedItem().getNumCatalog()));
            Log.msg(0, "editNumPos" + event.getNewValue() + " id:" + event.getRowValue().getId());
        });

        text.setCellValueFactory(cellData -> cellData.getValue().textProperty());
        text.setCellFactory(TextFieldTableCell.forTableColumn());
        text.setOnEditCommit((event) ->{
            Sql_dbReference.updateReferenceText(event.getNewValue(), event.getRowValue().getId());
            table.setItems(Sql_dbReference.getReferenceByNumCat(cb.getSelectionModel().getSelectedItem().getNumCatalog()));
            Log.msg(0, "editText" + event.getNewValue() + " id:" + event.getRowValue().getId());
        });
             
        prix.setCellValueFactory(cellData -> cellData.getValue().prixProperty());
        prix.setCellFactory(TextFieldTableCell.forTableColumn());
        prix.setOnEditCommit((event) ->{
            Sql_dbReference.updateReferencePrix(event.getNewValue(), event.getRowValue().getId());
            table.setItems(Sql_dbReference.getReferenceByNumCat(cb.getSelectionModel().getSelectedItem().getNumCatalog()));
            Log.msg(0, "editPrix" + event.getNewValue() + " id:" + event.getRowValue().getId());
        });
        
        annee.setCellValueFactory(cellData -> cellData.getValue().yearCatalogProperty());
        annee.setCellFactory(TextFieldTableCell.forTableColumn());
        
        table.setItems(Sql_dbReference.getReferenceByNumCat(cb.getSelectionModel().getSelectedItem().getNumCatalog()));
    }
                
    private void updateValue(int num){
        table.setItems(Sql_dbReference.getReferenceByNumCat(cb.getSelectionModel().getSelectedItem().getNumCatalog()));        
    }
}
