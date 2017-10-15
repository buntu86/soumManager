package com.soumManager.view;

import com.agenda.model.Adresse;
import com.agenda.model.Agenda;
import com.soumManager.utils.Log;
import com.soumManager.utils.Tools;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AgendaEditController implements Initializable {
    @FXML
    private TextField tf_nom1;
    @FXML
    private TextField tf_nom2;
    @FXML
    private TextField tf_adresse1;
    @FXML
    private TextField tf_adresse2;
    @FXML
    private TextField tf_npa;
    @FXML
    private TextField tf_lieu;    
    @FXML
    private TextField tf_tel1;    
    @FXML
    private TextField tf_tel2;    
    @FXML
    private TextField tf_mail;
    
    private IntegerProperty id = new SimpleIntegerProperty(0);
    private Stage stage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.addListener((ob,n,n1) -> updateTextField());
    }    

    public void setStage(Stage agendaEditStage) {
        this.stage=agendaEditStage;
    }
    
    public void setId(int idSelected) {
        this.id.set(idSelected);
    }
    
    private int getId(){
        return this.id.get();
    }
    
    private void updateTextField(){
        Log.msg(0, "ok");
        Adresse tmpAdr = Agenda.getAdresseById(getId());
        tf_nom1.setText(tmpAdr.getNom1());
        tf_nom2.setText(tmpAdr.getNom2());
        tf_adresse1.setText(tmpAdr.getAdresse1());
        tf_adresse2.setText(tmpAdr.getAdresse2());
        tf_npa.setText(Tools.intToString(tmpAdr.getNpa()));
        tf_lieu.setText(tmpAdr.getLieu()); 
        tf_tel1.setText(tmpAdr.getTel1());
        tf_tel2.setText(tmpAdr.getTel2());
        tf_mail.setText(tmpAdr.getMail());        
    }

    @FXML
    private void actionBtnModifier(){
    }
    @FXML
    private void actionBtnSupprimer(){
    }    
    @FXML
    private void actionBtnFermer(){
        stage.close();
    }        
}
