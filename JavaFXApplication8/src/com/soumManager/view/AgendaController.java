package com.soumManager.view;

import com.agenda.model.Adresse;
import com.agenda.model.Adresse_contact;
import com.agenda.model.Adresse_type;
import com.agenda.model.Agenda;
import com.agenda.model.Tree_objectPointer;
import com.soumManager.model.Position21;
import com.soumManager.utils.Log;
import com.soumManager.utils.Tools;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class AgendaController implements Initializable {

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
    private TreeView<Tree_objectPointer> tree;
    @FXML
    private TableView<Adresse_contact> tableview;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        disableTextField();

        List<Tree_objectPointer> listTypes = new ArrayList<>();
        
        for(Adresse_type obj : Agenda.getListeTypes()){
            listTypes.add(new Tree_objectPointer(obj.getId(), obj.getCategorie(), "type"));
        }        
        
        TreeItem<Tree_objectPointer> root = new TreeItem<>();
        for(Tree_objectPointer level1 : listTypes){
            TreeItem<Tree_objectPointer> level1TreeItem = new TreeItem<> (level1);
            for(Adresse level2 : Agenda.getListeAdresses_byIdTypes(level1.getId())){
                Tree_objectPointer tmp = new Tree_objectPointer(level2.getId(), level2.getNom1(), "adresse");
                TreeItem<Tree_objectPointer> level2TreeItem = new TreeItem<> (tmp);
                level1TreeItem.getChildren().add(level2TreeItem);
            }
            
            root.getChildren().add(level1TreeItem);
        }

        tree.setShowRoot(false);
        tree.setRoot(root);     
        
        tree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> actionWhenTreeIsSelected(newValue.getValue()));
    }    
    
    private void actionWhenTreeIsSelected(Tree_objectPointer tObPo){
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
            
            Log.msg(0, "type " + tObPo.getId());
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
            Log.msg(0, "adresse " + tObPo.getId());
        }
        else
            disableTextField();
    }
    
    private void disableTextField(){
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
    }    
}

/*
    private TreeView<String> rootTree;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    void iniCatalog(String str) {
        Catalog.setCatalog(str);
        Tools.setTitlePrimaryStage(Catalog.getTitle());
        TreeItem<String> tree = new TreeItem<> ("CAN");
        
        for(Articles artLev1 : Catalog.getLevel1()){
            TreeItem<String> level1 = new TreeItem<> (artLev1.getPos() + " - " + artLev1.getTextTitle());
            
            for(Articles artLev2 : Catalog.getLevel2(artLev1.getPosInt())){
                TreeItem<String> level2 = new TreeItem<> (artLev2.getPos() + " - " + artLev2.getTextTitle());
            
                for(Articles artLev3 : Catalog.getLevel3(artLev2.getPosInt())){
                    TreeItem<String> level3 = new TreeItem<> (artLev3.getPos() + " - " + artLev3.getTextTitle());
                    level2.getChildren().add(level3);
                }
                level1.getChildren().add(level2);
            }
            tree.getChildren().add(level1);
        }
        
        rootTree.setRoot(tree);
        rootTree.setShowRoot(false);
    }
*/
