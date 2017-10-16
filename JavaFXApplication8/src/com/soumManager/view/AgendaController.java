package com.soumManager.view;

import com.agenda.model.Adresse;
import com.agenda.model.Agenda;
import com.soumManager.MainApp;
import com.soumManager.utils.Log;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AgendaController implements Initializable{

    @FXML
    private TextField tf_search;
    @FXML
    private ListView<Adresse> list = new ListView<Adresse>();
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_edit;
    @FXML
    private Button btn_fermer;
        
    private Stage agendaStage;
    private int idSelected = 0;
    private String search = "";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateList("");
        btn_edit.setDisable(true);
        tf_search.textProperty().addListener((observable, oldValue, newValue) -> {
            updateList(newValue);
            this.search = newValue;
                });
        list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null)
            {
                btn_edit.setDisable(false);
                idSelected = newValue.getId();
            }
            else
                idSelected = 0;
        });
    }    

    public void setStage(Stage agendaStage) {
        this.agendaStage = agendaStage;
    }
    
    private void updateList(String str){
        list.setItems(Agenda.getListeAdressesFromSearch(str)); 
    }
    
    @FXML
    private void closeStage(){
        agendaStage.close();
    }
    
    @FXML
    private void openAgendaEdit(){
        AnchorPane agendaEditLayout;        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/soumManager/view/AgendaEdit.fxml"));
            agendaEditLayout = (AnchorPane) loader.load();  
            AgendaEditController controller = loader.getController();
            Stage agendaEditStage = new Stage();
            controller.setStage(agendaEditStage);            
            controller.setId(idSelected);
            controller.setAction("edit");
            agendaEditStage.setTitle("Agenda - édition");
            agendaEditStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(agendaEditLayout);
            agendaEditStage.setScene(scene);                                

            agendaEditStage.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent t) -> {
                if(t.getCode()==KeyCode.ESCAPE)
                {
                    agendaEditStage.close();
                    Log.msg(0, "AgendaEdit : esc");
                }
            });
            agendaEditStage.showAndWait();
            updateList(this.search);

        } catch (Exception e) {
            Log.msg(1, "AgendaEdit | " + e.getMessage());
        }                
    }
    @FXML
    private void openAgendaAdd(){
        AnchorPane agendaEditLayout;        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/soumManager/view/AgendaEdit.fxml"));
            agendaEditLayout = (AnchorPane) loader.load();  
            AgendaEditController controller = loader.getController();
            Stage agendaEditStage = new Stage();
            controller.setStage(agendaEditStage);            
            controller.setId(0);
            controller.setAction("add");
            agendaEditStage.setTitle("Agenda - ajout");
            agendaEditStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(agendaEditLayout);
            agendaEditStage.setScene(scene);                                

            agendaEditStage.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent t) -> {
                if(t.getCode()==KeyCode.ESCAPE)
                {
                    agendaEditStage.close();
                    Log.msg(0, "AgendaEdit : esc");
                }
            });                       
            
            agendaEditStage.showAndWait();
            updateList(this.search);            

        } catch (Exception e) {
            Log.msg(1, "AgendaEdit | " + e.getMessage());
        }                
    }    
}
