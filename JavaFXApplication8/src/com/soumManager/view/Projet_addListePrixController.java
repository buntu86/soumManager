package com.soumManager.view;

import com.agenda.data.Sql_agenda;
import com.agenda.model.Adresse;
import com.soumManager.utils.Log;
import com.soumManager.utils.Tools;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
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
        if(!tf_titre.getText().trim().equals(""))
        {
            Log.msg(0, "Save");
            close();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur - Ajout liste de prix");
            alert.setHeaderText(null);
            alert.setContentText("Erreur : le champs titre et le choix de l'entreprise est obligatoire.");
            alert.showAndWait(); 
        }
        
    };
    
    @FXML
    private void close(){
        this.stage.close();
    }
}
