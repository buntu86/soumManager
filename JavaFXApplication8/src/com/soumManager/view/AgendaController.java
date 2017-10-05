package com.soumManager.view;

import com.agenda.model.Adresse;
import com.agenda.model.Adresse_contact;
import com.agenda.model.Adresse_type;
import com.agenda.model.Agenda;
import com.agenda.model.Tree_objectPointer;
import com.soumManager.utils.Log;
import com.soumManager.utils.Tools;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class AgendaController implements Initializable{

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
    private TreeView<Tree_objectPointer> tree = new TreeView<>();
    @FXML
    private TableView<Adresse_contact> tableview;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_fermer;
    
    private int idTypeSelected = 0, idAdresseSelected = 0;
    private TreeItem<Tree_objectPointer> root = new TreeItem<>();    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        disableTextField();
        generateTree();
        tree.setRoot(root);
        tree.setShowRoot(false);
        tree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> actionWhenTreeIsSelected(newValue.getValue()));                                        
    }    
    
   private void generateTree() {

        if(root.getChildren().size()>0)
            root.getChildren().clear();
       
        List<Tree_objectPointer> listTypes = new ArrayList<>();        
        for(Adresse_type obj : Agenda.getListeTypes()){
            listTypes.add(new Tree_objectPointer(obj.getId(), obj.getCategorie(), "type"));
        }        
        
        for(Tree_objectPointer level1 : listTypes){
            TreeItem<Tree_objectPointer> level1TreeItem = new TreeItem<> (level1);
            for(Adresse level2 : Agenda.getListeAdresses_byIdTypes(level1.getId())){
                Tree_objectPointer tmp = new Tree_objectPointer(level2.getId(), level2.getNom1(), "adresse");
                TreeItem<Tree_objectPointer> level2TreeItem = new TreeItem<> (tmp);
                level1TreeItem.getChildren().add(level2TreeItem);
            }
            root.getChildren().add(level1TreeItem);
        }
    }
   
    //BUTTON
    @FXML
    private void actionBtnAjouter(){
        if(!tf_nom1.getText().trim().isEmpty())
        {
            Agenda.addAdresse(new Adresse(0,
                    tf_nom1.getText().trim(),
                    tf_nom2.getText().trim(),
                    tf_adresse1.getText().trim(),
                    tf_adresse2.getText().trim(),
                    tf_lieu.getText().trim(),
                    Tools.stringToInt(tf_npa.getText().trim()),
                    tf_tel1.getText().trim(),
                    tf_tel2.getText().trim(),
                    tf_mail.getText().trim(),
                    idTypeSelected)); 
            generateTree();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur - ajout adresse");
            alert.setHeaderText(null);
            alert.setContentText("Le champs nom est obligatoire");
            alert.showAndWait();        
        }
    }
    @FXML
    private void actionBtnModifier(){
    
    }
    @FXML
    private void actionBtnSupprimer(){
        if(idAdresseSelected!=0)
            Agenda.delAdresse(idAdresseSelected);
        generateTree();
    }
    @FXML
    private void actionBtnFermer(){
        disableTextField();
    }    
   
    //TEXTFIELD
    private void actionWhenTreeIsSelected(Tree_objectPointer tObPo){
        if(tObPo!=null)
        {
            if(tObPo.getType().equals("type"))
            {
                enableTextField();
                tf_nom1.clear();
                tf_nom2.clear();
                tf_adresse1.clear();
                tf_adresse2.clear();
                tf_npa.clear();
                tf_lieu.clear();
                tf_tel1.clear();
                tf_tel2.clear();
                tf_mail.clear();
                btn_ajouter.setVisible(true);
                btn_supprimer.setVisible(false);
                btn_modifier.setVisible(false);
                idTypeSelected = tObPo.getId();
                idAdresseSelected = 0;
            }

            else if(tObPo.getType().equals("adresse"))
            {
                Adresse adresseShow = Agenda.getAdresseById(tObPo.getId());
                enableTextField();
                tf_nom1.setText(adresseShow.getNom1());
                tf_nom2.setText(adresseShow.getNom2());
                tf_adresse1.setText(adresseShow.getAdresse1());
                tf_adresse2.setText(adresseShow.getAdresse2());
                tf_npa.setText(Tools.intToString(adresseShow.getNpa()));
                tf_tel1.setText(adresseShow.getTel1());
                tf_tel2.setText(adresseShow.getTel2());
                tf_mail.setText(adresseShow.getMail());
                idAdresseSelected = adresseShow.getId();
                btn_modifier.setVisible(true); 
                btn_supprimer.setVisible(true);
                btn_ajouter.setVisible(false);
            }
            else
                disableTextField();
        }
        else
            disableTextField();        
    }
    
    private void disableTextField(){
        idAdresseSelected = 0;
        tf_nom1.clear();
        tf_nom2.clear();
        tf_adresse1.clear();
        tf_adresse2.clear();
        tf_npa.clear();
        tf_lieu.clear();
        tf_tel1.clear();
        tf_tel2.clear();
        tf_mail.clear();        
        tf_nom1.setDisable(true);
        tf_nom2.setDisable(true);
        tf_adresse1.setDisable(true);
        tf_adresse2.setDisable(true);
        tf_lieu.setDisable(true);
        tf_npa.setDisable(true);
        tf_tel1.setDisable(true);
        tf_tel2.setDisable(true);
        tf_mail.setDisable(true);
        tableview.setDisable(true);
        btn_ajouter.setVisible(false);
        btn_modifier.setVisible(false);
        btn_supprimer.setVisible(false);
        btn_fermer.setDisable(true);
    }
    
    private void enableTextField(){
        tf_nom1.setDisable(false);
        tf_nom2.setDisable(false);
        tf_adresse1.setDisable(false);
        tf_adresse2.setDisable(false);
        tf_lieu.setDisable(false);
        tf_npa.setDisable(false);
        tf_tel1.setDisable(false);
        tf_tel2.setDisable(false);
        tf_mail.setDisable(false);
        tableview.setDisable(false); 
        btn_fermer.setDisable(false);
    }    
}
