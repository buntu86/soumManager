package com.soumManager.view;

import com.agenda.data.Sql_agenda;
import com.agenda.model.Adresse;
import com.soumManager.data.Sql_Projet;
import com.soumManager.model.ListePrix;
import com.soumManager.utils.Log;
import com.soumManager.utils.Tools;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Projet_addListePrixController implements Initializable {

    private Stage stage;
    private RootLayoutController rootLayout;
    
    @FXML
    private TextField tf_titre;
    @FXML
    private TextField tf_date;
    @FXML
    private TextField tf_rabais1;    
    @FXML
    private TextField tf_rabais2;
    @FXML
    private TextField tf_escompte;
    @FXML
    private TextField tf_remarques;
    @FXML
    private ComboBox<Adresse> comboBox;
    private TableView<ListePrix> tableView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    void setStage(Stage addListePrixStage) {
        this.stage = addListePrixStage;
    }

    void setRootLayout(RootLayoutController rootLayout) {
        this.rootLayout = rootLayout;
        comboBox.setItems(Sql_agenda.getAdresses());
        tf_date.setText(Tools.ConvertDateToLisible((String.valueOf(System.currentTimeMillis()/1000))));
        
    }   
    
    @FXML
    private void save(){
        if(!tf_titre.getText().trim().equals("")){
            if(!comboBox.getSelectionModel().isEmpty() && comboBox.getSelectionModel().getSelectedItem().getId()>0){
                Sql_Projet.addListePrix(new ListePrix(
                        tf_titre.getText(),
                        comboBox.getSelectionModel().getSelectedItem().getId(), 
                        Tools.stringToInt(tf_rabais1.getText()),
                        Tools.stringToInt(tf_rabais2.getText()),
                        Tools.stringToInt(tf_escompte.getText()),
                        tf_remarques.getText(),
                        Tools.ConvertDateToSecond(tf_date.getText())));
                Log.msg(0, "Save");
                close();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur - Ajout liste de prix");
                alert.setHeaderText(null);
                alert.setContentText("Erreur : le choix de l'entreprise est obligatoire.");
                alert.showAndWait();             
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur - Ajout liste de prix");
            alert.setHeaderText(null);
            alert.setContentText("Erreur : le champs titre est obligatoire.");
            alert.showAndWait(); 
        }
    };
    
    @FXML
    private void close(){
        this.stage.close();
    }

    void setTableView(TableView<ListePrix> tableView) {
        this.tableView = tableView;
    }
}
