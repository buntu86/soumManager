package com.soumManager.view;

import com.agenda.model.Adresse;
import com.agenda.model.Adresse_type;
import com.agenda.model.Agenda;
import com.soumManager.model.Position21;
import com.soumManager.utils.Log;
import java.net.URL;
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
    private TreeView<String> tree;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TreeItem<Adresse_type> root = new TreeItem<Adresse_type>();
        TreeItem<String> root = new TreeItem<> ("Agenda");
        /*for(Adresse_type type : Agenda.getListeTypes()){
            TreeItem<Adresse_type> level1 = new TreeItem<> (type);
            root.getChildren().add(root)
            
            /*for(Adresse adresse : Agenda.getListeAdresses_byIdTypes(type.getId())){
                TreeItem<String> level2 = new TreeItem<> (adresse.getNom1());
                //level1.getChildren().add(level2);
            }
            
        }*/
        //tree.setShowRoot(false);
        tree.setRoot(root);     
        
        //tree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> Log.msg(0, newValue.getValue()));
        
        //rootTree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showArticlesDetails(newValue.getValue()));        
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
