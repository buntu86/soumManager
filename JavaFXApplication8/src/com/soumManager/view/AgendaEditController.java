package com.soumManager.view;

import com.agenda.model.Adresse;
import com.agenda.model.Adresse_type;
import com.agenda.model.Agenda;
import com.soumManager.utils.Log;
import com.soumManager.utils.Tools;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_edit;    
    @FXML
    private Button btn_delete;
    @FXML
    private ChoiceBox<Adresse_type> choiceBox;
    
    private IntegerProperty id = new SimpleIntegerProperty(0);
    private StringProperty action = new SimpleStringProperty("");
    private Stage stage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.addListener((ob,n,n1) -> updateTextField());
        action.addListener((ob,n,n1) -> checkAction());
        choiceBox.setItems(Agenda.getListeTypes());
    }

    public void setStage(Stage agendaEditStage) {
        this.stage=agendaEditStage;
    }
    
    public void setId(int idSelected) {
        this.id.set(idSelected);
    }
    
    public void setAction(String str) {
        this.action.set(str);
    }
    
    private String getAction(){
        return this.action.get();
    }
    
    private int getId(){
        return this.id.get();
    }
    
    private void checkAction(){
        btn_edit.setVisible(false);
        btn_delete.setVisible(false);
        btn_ajouter.setVisible(false);
        if(getAction().equals("add"))
        {
            btn_ajouter.setVisible(true);
            choiceBox.selectionModelProperty().get().selectFirst();
        }
        if(getAction().equals("edit"))
        {
            btn_edit.setVisible(true);
            btn_delete.setVisible(true);
        }        
    }
    
    private void updateTextField(){
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
        //choiceBox.getSelectionModel().select
    }

    @FXML
    private void actionBtnModifier(){
        Agenda.updateAdresse(getId(), new Adresse(0, 
                tf_nom1.getText(), 
                tf_nom2.getText(), 
                tf_adresse1.getText(), 
                tf_adresse2.getText(), 
                tf_lieu.getText(), 
                Tools.stringToInt(tf_npa.getText()),
                tf_tel1.getText(),
                tf_tel2.getText(),
                tf_mail.getText(),
                1));
        stage.close();
    }
    @FXML
    private void actionBtnSupprimer(){
        Agenda.delAdresse(getId());
        stage.close();
    }    
    @FXML
    private void actionBtnAdd(){
        Agenda.addAdresse(new Adresse(0, 
                tf_nom1.getText(), 
                tf_nom2.getText(), 
                tf_adresse1.getText(), 
                tf_adresse2.getText(), 
                tf_lieu.getText(), 
                Tools.stringToInt(tf_npa.getText()),
                tf_tel1.getText(),
                tf_tel2.getText(),
                tf_mail.getText(),
                choiceBox.getSelectionModel().getSelectedItem().getId()));
        stage.close();
    }
    @FXML
    private void actionBtnFermer(){
        stage.close();
    }        
}
