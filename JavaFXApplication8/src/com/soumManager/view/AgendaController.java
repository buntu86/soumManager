package com.soumManager.view;

import com.agenda.model.Adresse;
import com.agenda.model.Adresse_type;
import com.agenda.model.Agenda;
import com.agenda.model.Tree_objectPointer;
import com.soumManager.model.Position21;
import com.soumManager.utils.Log;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Tree_objectPointer> listTypes = new ArrayList<>();
        
        for(Adresse_type obj : Agenda.getListeTypes()){
            listTypes.add(new Tree_objectPointer(obj.getId(), obj.getCategorie(), "type"));
        }        
        
        TreeItem<Tree_objectPointer> root = new TreeItem<>();
        for(Tree_objectPointer level1 : listTypes){
            Log.msg(0, level1.getName());    
            TreeItem<Tree_objectPointer> level1TreeItem = new TreeItem<> (level1);
            
            for(Adresse level2 : Agenda.getListeAdresses_byIdTypes(level1.getId())){
                
                Tree_objectPointer tmp = new Tree_objectPointer(0, level2.getNom1(), "adresse");
                TreeItem<Tree_objectPointer> level2TreeItem = new TreeItem<> (tmp);
                level1TreeItem.getChildren().add(level2TreeItem);
                Log.msg(0, level2.getNom1());
            }
            
            root.getChildren().add(level1TreeItem);
        }

        tree.setShowRoot(false);
        tree.setRoot(root);     
        
        tree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> Log.msg(0, newValue.getValue().getType()));
        
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
