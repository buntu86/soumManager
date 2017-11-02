package com.soumManager.view;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Projet_newController implements Initializable {

    private Stage stage;
    @FXML
    private TextField tf_num;
    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_path;
    @FXML
    private Button save;
    private RootLayoutController rootLayout;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        save.setDisable(true);
        tf_path.textProperty().addListener((ob, old, new1) -> buttonEnable());
        tf_nom.textProperty().addListener((ob, old, new1) -> buttonEnable());
    }    
    
    @FXML
    private void save(){
        close();
        rootLayout.newProjet(tf_num.getText(), tf_nom.getText(), tf_path.getText());
    }
    
    @FXML
    private void close(){
        stage.close();
        
    }
    
    @FXML
    private void fileSearch(){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Enregristrement projet");        
        chooser.setInitialFileName(tf_num.getText() + " " + tf_nom.getText());
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Projet soumManager", "*.psm"));
        File selectedFile = chooser.showSaveDialog(null);
        
        if(selectedFile!=null)
            tf_path.setText(selectedFile.toString());
    }
    private void buttonEnable() {
        if(!tf_path.getText().trim().isEmpty() && !tf_nom.getText().trim().isEmpty())
            save.setDisable(false);
        else
            save.setDisable(true);
    }

    void setStage(Stage projetNewStage) {
        this.stage = projetNewStage;
    }
    void setRootLayout(RootLayoutController aThis) {
        this.rootLayout = aThis;
    }
}
